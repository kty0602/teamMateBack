package com.example.teammate.service;

import com.example.teammate.dto.UserSkillDTO;
import com.example.teammate.entity.Skill;
import com.example.teammate.entity.User;
import com.example.teammate.entity.UserSkill;

import java.util.List;

public interface UserSkillService {

    List<Long> register(List<UserSkillDTO> userSkillDTOList);

    void delete(Long idx);

    List<UserSkillDTO> getUserSkillsByUserId(Long userId);

    default UserSkill dtoToEntity(UserSkillDTO userSkillDTO) {
        User user = User.builder().idx(userSkillDTO.getSUser()).build();
        Skill skill = Skill.builder().idx(userSkillDTO.getSSkill()).build();

        UserSkill userSkill = UserSkill.builder()
                .idx(userSkillDTO.getIdx())
                .sUser(user)
                .sSkill(skill)
                .build();
        return userSkill;
    }

    default UserSkillDTO entityToDTO(UserSkill userSkill, Skill skill, User user) {
        UserSkillDTO dto = UserSkillDTO.builder()
                .idx(userSkill.getIdx())
                .sUser(user.getIdx())
                .sSkill(skill.getIdx())
                .build();
        return dto;
    }

}
