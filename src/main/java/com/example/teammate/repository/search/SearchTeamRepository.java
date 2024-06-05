package com.example.teammate.repository.search;


import com.example.teammate.dto.PageRequestDTO;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchTeamRepository {

    Page<Object[]> getSearch(PageRequestDTO requestDTO, Pageable pageable);
}
