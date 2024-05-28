package com.example.teammate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "Reply")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String rcontent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board rBoard; // 댓글이 작성된 게시글 번호
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User rUser;
    private boolean rdelete;
    public void changeContent(String rcontent) { this.rcontent = rcontent; }
    public void changeDelete(boolean rdelete) { this.rdelete = rdelete; }
}
