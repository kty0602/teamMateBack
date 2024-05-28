package com.example.teammate.controller;

import com.example.teammate.dto.UserSkillDTO;
import com.example.teammate.entity.UserSkill;
import com.example.teammate.service.UserSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class UserSkillController {

    private final UserSkillService userSkillService;

    @PostMapping("/stack")
    public ResponseEntity<List<Long>> register(@RequestBody List<UserSkillDTO> userSkillDTOList) {
        List<Long> userSkillIds = userSkillService.register(userSkillDTOList);
        return ResponseEntity.ok(userSkillIds);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long idx) {
        userSkillService.delete(idx);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user")
    public List<UserSkillDTO> getUserSkillsByUserId(@RequestParam Long userId) {
        return userSkillService.getUserSkillsByUserId(userId);
    }
}
