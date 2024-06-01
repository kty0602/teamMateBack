package com.example.teammate.dto;

import com.example.teammate.entity.Board;
import com.example.teammate.entity.User;
import lombok.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamReplyDTO {
    private Long idx;
    private String trcontent;
    private Long trTeam;
    private Long trUser;
    private String trnickname;
    private LocalDate regDate;
    private LocalDate modDate;
    @Builder.Default
    private boolean trdelete = true;
}
