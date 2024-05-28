package com.example.teammate.repository;

import com.example.teammate.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    @Query("SELECT us FROM UserSkill us WHERE us.sUser.idx = :userId")
    List<UserSkill> findByUserId(@Param("userId") Long userId);
}
