package com.example.teammate.service;

import com.example.teammate.dto.ReplyDTO;
import com.example.teammate.entity.Board;
import com.example.teammate.entity.Reply;
import com.example.teammate.entity.User;

import java.util.List;

public interface ReplyService {
    // 댓글 등록 처리
    Long register(ReplyDTO replyDTO);

    // 댓글 조회 처리
    List<ReplyDTO> getList(Long bno);

    // 댓글 수정 처리
    void modify(ReplyDTO replyDTO);

    // 댓글 삭제 처리
    void remove(ReplyDTO replyDTO);

    default Reply dtoToEntity(ReplyDTO replyDTO) {
        Board board = Board.builder().idx(replyDTO.getRBoard()).build();
        User user = User.builder().idx(replyDTO.getRUser()).build();

        Reply reply = Reply.builder()
                .idx(replyDTO.getIdx())
                .rcontent(replyDTO.getRcontent())
                .rUser(user)
                .rBoard(board)
                .rdelete(replyDTO.isRdelete())
                .build();
        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply, User user) {
        ReplyDTO dto = ReplyDTO.builder()
                .idx(reply.getIdx())
                .rcontent(reply.getRcontent())
                .rdelete(reply.isRdelete())
                .rnickname(user.getName())
                .rUser(reply.getRUser().getIdx())
                .rBoard(reply.getRBoard().getIdx())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return dto;
    }
}
