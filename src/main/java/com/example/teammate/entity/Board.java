package com.example.teammate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
@Table(name = "Board")  //커뮤니티
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User bUser;
    private String btitle;
    private String bcontent;
    private boolean bdelete;
    private String link;

    public void changeTitle(String btitle) {
        this.btitle = btitle;
    }
    public void changeContent(String bcontent) {
        this.bcontent = bcontent;
    }
    public void changeDelete(boolean bdelete) {
        this.bdelete = bdelete;
    }

}
