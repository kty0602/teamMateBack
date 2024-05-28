package com.example.teammate.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSkillDTO {
    private Long idx;
    private Long sUser;
    private Long sSkill;
}
