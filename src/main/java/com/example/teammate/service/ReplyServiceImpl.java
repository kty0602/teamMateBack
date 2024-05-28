package com.example.teammate.service;

import com.example.teammate.dto.*;
import com.example.teammate.entity.*;
import com.example.teammate.repository.BoardRepository;
import com.example.teammate.repository.ReplyRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    // 댓글 등록 처리
    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        Board board = boardRepository.findById(replyDTO.getRBoard())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. bno=" + replyDTO.getRBoard()));
        reply.setRBoard(boardRepository.save(board));
        replyRepository.save(reply);
        return reply.getIdx();
    }

    // 댓글 목록
    @Override
    public List<ReplyDTO> getList(Long idx) {
        List<Reply> result = replyRepository.getReply(Board.builder().idx(idx).build());

        return result.stream().map(reply -> {
            User user = reply.getRUser();
            return entityToDTO(reply, user);
        }).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = replyRepository.getReferenceById(replyDTO.getIdx());

        reply.changeContent(replyDTO.getRcontent());

        replyRepository.save(reply);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void remove(ReplyDTO replyDTO) {
        Reply reply  =replyRepository.getReferenceById(replyDTO.getIdx());

        if(replyDTO.isRdelete() == true) {
            reply.changeDelete(false);
            replyRepository.save(reply);
        }
    }
}
