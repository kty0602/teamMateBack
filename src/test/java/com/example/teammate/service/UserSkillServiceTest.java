package com.example.teammate.service;

import com.example.teammate.dto.UserSkillDTO;
import com.example.teammate.entity.Skill;
import com.example.teammate.entity.User;
import com.example.teammate.entity.UserSkill;
import com.example.teammate.repository.UserRepository;
import com.example.teammate.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserSkillServiceTest {

//    @Test
//    void testDtoToEntity() {
//        UserRepository userRepository = Mockito.mock(UserRepository.class);
//        SkillRepository skillRepository = Mockito.mock(SkillRepository.class);
//        UserSkillService service = new UserSkillService();
//        service.userRepository = userRepository;
//        service.skillRepository = skillRepository;
//
//        UserSkillDTO dto = new UserSkillDTO();
//        dto.setSUser(1L);
//        dto.setSSkill(2L);
//
//        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
//        Mockito.when(skillRepository.findById(2L)).thenReturn(Optional.of(new Skill()));
//
//        UserSkill userSkill = service.dtoToEntity(dto);
//
//        assertNotNull(userSkill.getSUser());
//        assertNotNull(userSkill.getSSkill());
//    }
}
