package com.example.teammate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.*;
import org.springframework.ui.Model;
import com.example.teammate.dto.*;
import com.example.teammate.service.TeamService;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@Log4j2
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody TeamDTO teamDTO) {
        Long idx = teamService.register(teamDTO);
        return ResponseEntity.ok().body(idx);
    }

    @GetMapping({"/read", "/modify"})
    public ResponseEntity<TeamDTO> read(@ModelAttribute("reqeustDTO") PageRequestDTO pageRequestDTO, Long idx, Model model) {
        log.info("idx : " + idx);
        TeamDTO teamDTO = teamService.get(idx);
        log.info(teamDTO);
        model.addAttribute("dto", teamDTO);

        return new ResponseEntity<>(teamService.get(idx), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Long> remove(@RequestBody TeamDTO teamDTO) {
        teamService.remove(teamDTO);
        return ResponseEntity.ok(teamDTO.getIdx());
    }

    @PostMapping("/update")
    public ResponseEntity<Long> update(@RequestBody TeamDTO teamDTO) {
        teamService.update(teamDTO);
        return ResponseEntity.ok(teamDTO.getIdx());
    }

    @PostMapping("/modify")
    public ResponseEntity<Long> modify(@RequestBody TeamDTO teamDTO, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {

        log.info("post modify..................................");
        log.info("dto : "+ teamDTO);

        teamService.modify(teamDTO);

        redirectAttributes.addAttribute("page", requestDTO.getPage());

        redirectAttributes.addAttribute("idx", teamDTO.getIdx());
        return ResponseEntity.ok(teamDTO.getIdx());
    }

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<TeamDTO, Object[]>> list(@ModelAttribute PageRequestDTO requestDTO) {
        log.info("list request: " + requestDTO);
        PageResultDTO<TeamDTO, Object[]> result = teamService.getList(requestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
