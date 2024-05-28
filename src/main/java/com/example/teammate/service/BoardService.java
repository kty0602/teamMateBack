package com.example.teammate.service;

import com.example.teammate.dto.BoardDTO;
import com.example.teammate.dto.PageRequestDTO;
import com.example.teammate.dto.PageResultDTO;
import com.example.teammate.entity.Board;
import com.example.teammate.entity.User;

public interface BoardService {

    // 게시글 등록 서비스
    Long register(BoardDTO dto);

    // 게시글 목록 처리
    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 게시글 조회 처리
    BoardDTO get(Long bno);

    // 게시글 삭제 처리
    void remove(BoardDTO dto);

    // 게시글 수정 처리
    void modify(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        User user = User.builder().idx(dto.getBUser()).build();

        Board board = Board.builder()
                .idx(dto.getIdx())
                .btitle(dto.getBtitle())
                .bcontent(dto.getBcontent())
                .link(dto.getLink())
                .bdelete(dto.isBdelete())
                .bUser(user)
                .build();
        return board;
    }

    default BoardDTO entityToDTO(Board board, User user, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .idx(board.getIdx())
                .bUser(user.getIdx())
                .bnickname(user.getName())
                .btitle(board.getBtitle())
                .bcontent(board.getBcontent())
                .link(board.getLink())
                .bdelete(board.isBdelete())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return boardDTO;
    }
}
