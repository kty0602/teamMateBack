package com.example.teammate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "Contest")  //공모전
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
