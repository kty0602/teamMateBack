package com.example.teammate.controller;

import com.example.teammate.dto.*;
import com.example.teammate.service.*;
import org.springframework.http.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@RestController
@RequestMapping("/teamReply")
@Log4j2
@RequiredArgsConstructor
public class TeamReplyController {

    private final TeamReplyService teamReplyService;

    @GetMapping(value = "/team/{idx}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeamReplyDTO>> getListByTeam(@PathVariable("idx") Long idx) {
        log.info("idx: " + idx);
        List<TeamReplyDTO> teamReplyDTOList = teamReplyService.getList(idx);

        return new ResponseEntity<>(teamReplyService.getList(idx), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody TeamReplyDTO teamReplyDTO) {
        log.info(teamReplyDTO);

        Long idx = teamReplyService.register(teamReplyDTO);

        return new ResponseEntity<>(idx, HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Long> remove(@RequestBody TeamReplyDTO teamReplyDTO) {
        teamReplyService.remove(teamReplyDTO);
        return ResponseEntity.ok(teamReplyDTO.getIdx());
    }

    @PostMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody TeamReplyDTO teamReplyDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify..................................");
        log.info("dto : "+ teamReplyDTO);

        teamReplyService.modify(teamReplyDTO);

        redirectAttributes.addAttribute("idx", teamReplyDTO.getIdx());
        return ResponseEntity.ok(teamReplyDTO.getIdx());
    }

}
