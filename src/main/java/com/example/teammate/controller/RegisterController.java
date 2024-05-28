package com.example.teammate.controller;

import com.example.teammate.dto.UserDTO;
import com.example.teammate.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;


    @PostMapping("/user")
    public ResponseEntity<String> userRegister(@RequestBody UserDTO userDTO) {
        String result = registerService.RegisterUserProcess(userDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/admin")
    public ResponseEntity<String> adminRegister(@RequestBody UserDTO userDTO) {
        String result = registerService.RegisterAdminProcess(userDTO);
        return ResponseEntity.ok(result);
    }
}
