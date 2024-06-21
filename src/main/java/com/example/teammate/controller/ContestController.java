package com.example.teammate.controller;

import com.example.teammate.dto.*;
import com.example.teammate.entity.Contest;
import com.example.teammate.service.ContestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contest")
@RequiredArgsConstructor
@Log4j2
public class ContestController {

    private final ContestService contestService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<ContestDTO, Contest>> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list..................." + pageRequestDTO);
        model.addAttribute("result", contestService.getList(pageRequestDTO));

        return new ResponseEntity<>(contestService.getList(pageRequestDTO), HttpStatus.OK);
    }
}
