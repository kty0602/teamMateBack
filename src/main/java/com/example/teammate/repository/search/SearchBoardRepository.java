package com.example.teammate.repository.search;

import com.example.teammate.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
public interface SearchBoardRepository {
    Board search1();

    Page<Object[]> searchPage(String type, Pageable pageable);
}
