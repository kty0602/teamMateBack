package com.example.teammate.dto;

import lombok.*;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDTO {
    private Long idx;
    private String rcontent;
    private Long rBoard;
    private Long rUser;
    private String rnickname;
    private LocalDate regDate;
    private LocalDate modDate;
    @Builder.Default
    private boolean rdelete = true;
}
