package com.example.teammate.repository;

import com.example.teammate.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long>, QuerydslPredicateExecutor<Team> {
    // 특정 게시글에 대한 정보와 해당 게시글을 작성한 사용자 정보를 함께 조회
    @Query("select b, u from Team b left join b.tUser u  where b.idx =:idx")
    Object getTeamWithUser(@Param("idx") Long idx);

    // 특정 게시글에 대한 정보와 댓글 정보를 함께 조회
    @Query("select b, r from Team b left join TeamReply r on r.trTeam = b where b.idx = :idx")
    List<Object[]> getTeamWithTeamReply(@Param("idx") Long idx);

    // 게시글, 사용자 정보, 댓글의 수를 함께 조회
    // tdelete값이 false인 경우 존재하지 않는 글로 인식하여 조회에서 제외
    @Query(value = "select b, u, count(r) " +
            "from Team b " +
            "left join b.tUser u " +
            "left join TeamReply r on r.trTeam = b " +
            "where b.tdelete <> false " +
            "group by b",
            countQuery = "select count(b) from Team b")
    Page<Object[]> getTeamWithTeamReplyCount(Pageable pageable);

    // 특정 idx에 해당하는 게시글과 사용자 정보 댓글의 정보를 제공
    // 댓글을 삭제하여 false처리가 되어도 카운팅하는 현상을 수정
    // (r is null or r.rdelete <> false) -> false인 댓글을 카운팅하지 않는다.
    @Query("select b, u, count(r) " +
            "from Team b " +
            "left join b.tUser u " +
            "left outer join TeamReply r on r.trTeam = b " +
            "where b.idx = :idx and (r is null or r.trdelete <> false) group by b, u")
    Object getTeamByIdx(@Param("idx") Long idx);


}
