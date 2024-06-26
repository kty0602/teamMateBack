package com.example.teammate.repository;

import com.example.teammate.entity.TeamReply;
import com.example.teammate.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamReplyRepository extends JpaRepository<TeamReply, Long> {
    // 게시글 삭제 시 해당 게시글 댓글들 상태 false로 삭제 처리
    @Modifying
    @Query("update TeamReply r set r.trdelete = false where r.trTeam.idx =:idx")
    void deleteByIdx(Long idx);

    // idx 기준으로 trdelete값이 true에 해당하는 댓글들을 가져온다.
    @Query("select r from TeamReply r where r.trTeam = :trTeam and r.trdelete = true order by r.idx")
    List<TeamReply> getTeamReply(Team trTeam);
}
