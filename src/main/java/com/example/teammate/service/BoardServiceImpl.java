package com.example.teammate.service;

import com.example.teammate.dto.BoardDTO;
import com.example.teammate.dto.PageRequestDTO;
import com.example.teammate.dto.PageResultDTO;
import com.example.teammate.entity.Board;
import com.example.teammate.entity.User;
import com.example.teammate.repository.BoardRepository;
import com.example.teammate.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    // 게시글 등록 처리
    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);
        return board.getIdx();
    }

    // 게시글 목록 처리
    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board) en[0], (User) en[1], (Long) en[2]));
        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getBtype(),
                pageRequestDTO.getPageable(Sort.by("idx").descending()));

        return new PageResultDTO<>(result, fn);
    }

    // 게시글 조회 서비스
    @Override
    public BoardDTO get(Long idx) {
        Object result = boardRepository.getBoardByBno(idx);
        Object[] arr = (Object[])result;
        return entityToDTO((Board)arr[0], (User)arr[1], (Long)arr[2]);
    }

    // 게시글 삭제 처리
    @Transactional
    @Override
    public void remove(BoardDTO boardDTO) {
        Board board = boardRepository.getReferenceById(boardDTO.getIdx());

        if(boardDTO.isBdelete() == true) {
            board.changeDelete(false);
            replyRepository.deleteByBno(boardDTO.getIdx());
            boardRepository.save(board);
        } else {
            System.out.println("Board is not marked for deletion");
        }
    }

    // 게시글 수정 처리
    @Transactional
    @Override
    public void modify(BoardDTO dto) {
        Board board = boardRepository.getReferenceById(dto.getIdx());

        if(dto.getBtitle() != null) {
            board.changeTitle(dto.getBtitle());
        }
        if(dto.getBcontent() != null) {
            board.changeContent(dto.getBcontent());
        }
        boardRepository.save(board);
    }
}
