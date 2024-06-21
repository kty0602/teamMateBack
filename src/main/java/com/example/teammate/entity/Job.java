package com.example.teammate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "Job")  //
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String company;
    private String jtitle;

    // 요구 기술
    private String stack;
    private String place;

    // 신입, 경력
    private String career;

    // 대학 2~3
    private String education;
    private String date;
    private String pageLink;
}
