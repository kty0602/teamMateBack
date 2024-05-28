package com.example.teammate.repository;

import com.example.teammate.entity.Board;
import com.example.teammate.repository.search.SearchBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    // 특정 게시글에 대한 정보와 해당 게시글을 작성한 사용자 정보를 함께 조회
    @Query("select b, u from Board b left join b.bUser u  where b.idx =:idx")
    Object getBoardWithUser(@Param("idx") Long idx);

    // 특정 게시글에 대한 정보와 댓글 정보를 함께 조회
    @Query("select b, r from Board b left join Reply r on r.rBoard = b where b.idx = :idx")
    List<Object[]> getBoardWithReply(@Param("idx") Long idx);

    // 게시글, 사용자 정보, 댓글의 수를 함께 조회
    // bdelete값이 false인 경우 존재하지 않는 글로 인식하여 조회에서 제외
    @Query(value = "select b, u, count(r) " +
            "from Board b " +
            "left join b.bUser u " +
            "left join Reply r on r.rBoard = b " +
            "where b.bdelete <> false " +
            "group by b",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    // 특정 bno에 해당하는 게시글과 사용자 정보 댓글의 정보를 제공
    // 댓글을 삭제하여 false처리가 되어도 카운팅하는 현상을 수정
    // (r is null or r.rdelete <> false) -> false인 댓글을 카운팅하지 않는다.
    @Query("select b, u, count(r) " +
            "from Board b " +
            "left join b.bUser u " +
            "left outer join Reply r on r.rBoard = b " +
            "where b.idx = :idx and (r is null or r.rdelete <> false) group by b, u")
    Object getBoardByBno(@Param("idx") Long idx);
}
