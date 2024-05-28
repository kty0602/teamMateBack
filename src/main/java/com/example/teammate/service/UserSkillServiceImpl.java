package com.example.teammate.service;

import com.example.teammate.dto.UserSkillDTO;
import com.example.teammate.entity.Skill;
import com.example.teammate.entity.User;
import com.example.teammate.entity.UserSkill;
import com.example.teammate.repository.SkillRepository;
import com.example.teammate.repository.UserRepository;
import com.example.teammate.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSkillServiceImpl implements UserSkillService {


    private final UserRepository userRepository;

    private final SkillRepository skillRepository;

    private final UserSkillRepository userSkillRepository;

    @Override
    public List<Long> register(List<UserSkillDTO> userSkillDTOList) {
        List<Long> savedIds = new ArrayList<>();

        for (UserSkillDTO userSkillDTO : userSkillDTOList) {
            // DTO를 엔티티로 변환
            UserSkill userSkill = dtoToEntity(userSkillDTO);

            // 유효한 사용자와 기술인지 확인
            User user = userRepository.findById(userSkillDTO.getSUser())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userSkillDTO.getSUser()));
            Skill skill = skillRepository.findById(userSkillDTO.getSSkill())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid skill ID: " + userSkillDTO.getSSkill()));

            // 변환된 엔티티에 사용자와 기술 설정
            userSkill.setSUser(user);
            userSkill.setSSkill(skill);

            // 엔티티를 데이터베이스에 저장
            UserSkill savedUserSkill = userSkillRepository.save(userSkill);

            // 저장된 엔티티의 ID를 목록에 추가
            savedIds.add(savedUserSkill.getIdx());
        }

        return savedIds;
    }

    @Override
    public void delete(Long idx) {
        // idx로 엔티티를 찾음
        UserSkill userSkill = userSkillRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UserSkill ID: " + idx));

        // 엔티티를 데이터베이스에서 삭제
        userSkillRepository.delete(userSkill);
    }

    @Override
    public List<UserSkillDTO> getUserSkillsByUserId(Long userId) {
        List<UserSkill> userSkills = userSkillRepository.findByUserId(userId);
        return userSkills.stream()
                .map(userSkill -> new UserSkillDTO(userSkill.getIdx(), userSkill.getSUser().getIdx(), userSkill.getSSkill().getIdx()))
                .collect(Collectors.toList());
    }
}
