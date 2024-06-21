package com.example.teammate.controller;

import com.example.teammate.entity.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.ui.Model;
import com.example.teammate.dto.*;
import com.example.teammate.service.JobService;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
@Log4j2
public class JobController {

    private final JobService jobService;

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<JobDTO, Job>> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list"+pageRequestDTO);
        model.addAttribute("result", jobService.getList(pageRequestDTO));

        return new ResponseEntity<>(jobService.getList(pageRequestDTO), HttpStatus.OK);
    }
}
