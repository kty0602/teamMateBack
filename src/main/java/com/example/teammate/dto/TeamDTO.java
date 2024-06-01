package com.example.teammate.dto;

import lombok.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class TeamDTO {
    private Long idx;
    private Long tUser;
    private String tnickname;
    private String ttitle;
    private String tcontent;
    private String tskill;
    @Builder.Default
    private Integer state = 0;
    private LocalDate regDate;
    private LocalDate modDate;
    private int replyCount;
    @Builder.Default
    private boolean tdelete = true;

}
