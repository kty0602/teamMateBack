package com.example.teammate.service;

import com.example.teammate.dto.PageRequestDTO;
import com.example.teammate.dto.PageResultDTO;
import com.example.teammate.dto.TeamDTO;
import com.example.teammate.entity.Team;
import com.example.teammate.entity.User;
public interface TeamService {

    // 게시글 등록
    Long register(TeamDTO dto);

    // 목록 처리
    PageResultDTO<TeamDTO, Object[]> getList(PageRequestDTO requestDTO);

    // 게시글 1개 조회 처리
    TeamDTO get(Long idx);

    // 게시글 수정 처리
    void modify(TeamDTO dto);

    // 게시글 삭제 처리
    void remove(TeamDTO dto);

    // 게시글 모집 완료 변경 처리
    void update(TeamDTO dto);

    default Team dtoToEntity(TeamDTO dto) {
        User user = User.builder().idx(dto.getTUser()).build();

        Team team = Team.builder()
                .idx(dto.getIdx())
                .ttitle(dto.getTtitle())
                .tcontent(dto.getTcontent())
                .tskill(dto.getTskill())
                .state(dto.getState())
                .tdelete(dto.isTdelete())
                .tUser(user)
                .build();
        return team;
    }

    default TeamDTO entityToDTO(Team team, User user, Long replyCount) {

        TeamDTO teamDTO = TeamDTO.builder()
                .idx(team.getIdx())
                .tUser(user.getIdx())
                .tnickname(user.getName())
                .ttitle(team.getTtitle())
                .tcontent(team.getTcontent())
                .tskill(team.getTskill())
                .state(team.getState())
                .tdelete(team.isTdelete())
                .regDate(team.getRegDate())
                .modDate(team.getModDate())
                .replyCount(replyCount.intValue())
                .build();
        return teamDTO;
    }
}
