package com.example.teammate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "TeamReply")
public class TeamReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String trcontent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team trTeam; // 댓글이 작성된 게시글 번호
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User trUser;
    private boolean trdelete;
    public void changeContent(String trcontent) { this.trcontent = trcontent; }
    public void changeDelete(boolean trdelete) { this.trdelete = trdelete; }
}
