package com.example.teammate.service;

import com.example.teammate.dto.*;
import com.example.teammate.entity.*;
import java.util.List;
public interface TeamReplyService {

    // 댓글 등록 처리
    Long register(TeamReplyDTO teamReplyDTO);

    // 댓글 조회 처리
    List<TeamReplyDTO> getList(Long idx);

    // 댓글 수정 처리
    void modify(TeamReplyDTO teamReplyDTO);

    // 댓글 삭제 처리
    void remove(TeamReplyDTO teamReplyDTO);


    default TeamReply dtoToEntity(TeamReplyDTO teamReplyDTO) {
        Team team = Team.builder().idx(teamReplyDTO.getTrTeam()).build();
        User user = User.builder().idx(teamReplyDTO.getTrUser()).build();

        TeamReply teamReply = TeamReply.builder()
                .idx(teamReplyDTO.getIdx())
                .trcontent(teamReplyDTO.getTrcontent())
                .trUser(user)
                .trTeam(team)
                .trdelete(teamReplyDTO.isTrdelete())
                .build();
        return teamReply;
    }

    default TeamReplyDTO entityToDTO(TeamReply teamReply, User user) {
        TeamReplyDTO dto = TeamReplyDTO.builder()
                .idx(teamReply.getIdx())
                .trcontent(teamReply.getTrcontent())
                .trdelete(teamReply.isTrdelete())
                .trnickname(user.getName())
                .trUser(teamReply.getTrUser().getIdx())
                .trTeam(teamReply.getTrTeam().getIdx())
                .regDate(teamReply.getRegDate())
                .modDate(teamReply.getModDate())
                .build();
        return dto;
    }
}
