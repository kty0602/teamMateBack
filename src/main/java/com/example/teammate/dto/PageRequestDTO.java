package com.example.teammate.dto;

import lombok.*;
import org.springframework.data.domain.*;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    private int page;
    private int size;
    // 게시판 검색
    private String btype;


    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page -1, size, sort);
    }

}
