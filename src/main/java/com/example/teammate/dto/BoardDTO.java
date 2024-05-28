package com.example.teammate.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class BoardDTO {
    private Long idx;
    private Long bUser;
    private String btitle;
    private String bnickname;
    private String bcontent;
    private String link;
    private LocalDate regDate;
    private LocalDate modDate;
    private int replyCount;
    @Builder.Default
    private boolean bdelete = true;

}
