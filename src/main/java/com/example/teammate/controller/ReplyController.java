package com.example.teammate.controller;

import com.example.teammate.dto.*;
import com.example.teammate.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/reply")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value = "/board/{idx}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("idx") Long idx) {
        log.info("idx: " + idx);
        List<ReplyDTO> replyDTOList = replyService.getList(idx);
        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));

        return new ResponseEntity<>(replyService.getList(idx), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
        log.info(replyDTO);

        Long idx = replyService.register(replyDTO);

        return new ResponseEntity<>(idx, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Long> remove(@RequestBody ReplyDTO replyDTO) {

        replyService.remove(replyDTO);
        return ResponseEntity.ok(replyDTO.getIdx());
    }

    @PostMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody ReplyDTO replyDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify..................................");
        log.info("dto : "+ replyDTO);

        replyService.modify(replyDTO);

        redirectAttributes.addAttribute("idx", replyDTO.getIdx());
        return ResponseEntity.ok(replyDTO.getIdx());
    }

}
