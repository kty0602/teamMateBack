package com.example.teammate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "Team")
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User tUser;
    private String ttitle;
    private String tcontent;
    private String tskill;
    // 모집중, 모집완료를 표시하기 위함
    private Integer state;
    private boolean tdelete;


    public void changeState(Integer state) {this.state = state; }
    public void changeTitle(String ttitle) {
        this.ttitle = ttitle;
    }
    public void changeContent(String tcontent) {
        this.tcontent = tcontent;
    }
    public void changeDelete(boolean tdelete) {
        this.tdelete = tdelete;
    }
}
