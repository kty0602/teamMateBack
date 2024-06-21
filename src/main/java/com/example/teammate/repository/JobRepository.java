package com.example.teammate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.teammate.entity.Job;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface JobRepository extends JpaRepository<Job, Long>, QuerydslPredicateExecutor<Job> {
}
