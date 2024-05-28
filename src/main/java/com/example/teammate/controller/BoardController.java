package com.example.teammate.controller;

import com.example.teammate.dto.*;
import com.example.teammate.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<BoardDTO, Object[]>> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list..................." + pageRequestDTO);
        model.addAttribute("result", boardService.getList(pageRequestDTO));

        return new ResponseEntity<>(boardService.getList(pageRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody BoardDTO boardDTO) {
        Long idx = boardService.register(boardDTO);
        return ResponseEntity.ok().body(idx);
    }

    @GetMapping({"/read", "/modify"})
    public ResponseEntity<BoardDTO> read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long idx, Model model) {
        log.info("idx : " + idx);
        BoardDTO boardDTO = boardService.get(idx);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);

        return new ResponseEntity<>(boardService.get(idx), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Long> remove(@RequestBody BoardDTO boardDTO) {

        boardService.remove(boardDTO);
        return ResponseEntity.ok(boardDTO.getIdx());
    }

    @PostMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        log.info("post modify..................................");
        log.info("dto : "+ dto);

        boardService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());

        redirectAttributes.addAttribute("idx", dto.getIdx());
        return ResponseEntity.ok(dto.getIdx());
    }
}
