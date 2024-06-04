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

    // 팀 매칭 검색
    private String ktype;
    private String search;


    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page -1, size, sort);
    }

    // ktype 문자열을 배열로 변환하는 메서드
    public String[] getKtypeArray() {
        if (ktype == null || ktype.isEmpty()) {
            return new String[0];
        }
        return ktype.split(",");
    }

}
