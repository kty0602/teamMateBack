package com.example.teammate.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
public class UserDTO {
    private Long idx;
    @Column(unique = true)
    private String id;
    private String password;
    private String name;
    private String username;
    private String role;
}
