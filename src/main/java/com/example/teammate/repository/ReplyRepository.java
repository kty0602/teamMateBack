package com.example.teammate.repository;

import com.example.teammate.entity.Reply;
import com.example.teammate.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 게시글 삭제 시 해당 게시글 댓글들 상태 false로 삭제 처리
    @Modifying
    @Query("update Reply r set r.rdelete = false where r.rBoard.idx =:idx")
    void deleteByBno(Long idx);

    // rno 기준으로 rdelete값이 true에 해당하는 댓글들을 가져온다.
    @Query("select r from Reply r where r.rBoard = :rBoard and r.rdelete = true order by r.idx")
    List<Reply> getReply(Board rBoard);
}
