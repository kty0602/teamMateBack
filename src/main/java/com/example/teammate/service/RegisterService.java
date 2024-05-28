package com.example.teammate.service;

import com.example.teammate.dto.UserDTO;
import com.example.teammate.entity.User;
import com.example.teammate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    public String RegisterUserProcess(UserDTO userDTO) {

        //db에 이미 동일한 username을 가진 회원이 존재하는지?
        boolean isUser = userRepository.existsById(userDTO.getId());
        if (isUser) {
            return "fail";
        }

        User data = new User();

        data.setId(userDTO.getId());
        data.setPassword(userDTO.getPassword());
        data.setName(userDTO.getName());
        data.setUsername(userDTO.getId());
        data.setRole("ROLE_USER");


        userRepository.save(data);
        return "success";
    }

    public String RegisterAdminProcess(UserDTO userDTO) {

        boolean isUser = userRepository.existsById(userDTO.getId());
        if (isUser) {
            return "fail";
        }

        User data = new User();

        data.setId(userDTO.getId());
        data.setPassword(userDTO.getPassword());
        data.setName(userDTO.getName());
        data.setUsername("local");
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
        return "success";
    }

}
