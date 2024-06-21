package com.example.teammate.repository;

import com.example.teammate.entity.Contest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContestRepository extends JpaRepository<Contest, Long> {

    @Query("SELECT c FROM Contest c")
    Page<Contest> getContestList(Pageable pageable);
}
