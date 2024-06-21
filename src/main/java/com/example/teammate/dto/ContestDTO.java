package com.example.teammate.dto;

import lombok.*;

@Builder
@Getter
@Setter
public class ContestDTO {
    private Long idx;
    private String ctitle;
    private String pageLink;
    private String host;
    private String contestant;
    private String joinDate;
    private String reviewDate;
    private String day;
    private String status;
}
