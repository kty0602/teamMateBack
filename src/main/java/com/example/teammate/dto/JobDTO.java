package com.example.teammate.dto;

import lombok.*;

@Builder
@Getter
@Setter
public class JobDTO {
    private Long idx;
    private String company;
    private String jtitle;
    private String stack;
    private String place;
    private String career;
    private String education;
    private String date;
    private String pageLink;
}
