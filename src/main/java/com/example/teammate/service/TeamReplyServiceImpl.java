package com.example.teammate.service;

import com.example.teammate.dto.*;
import com.example.teammate.entity.*;
import com.example.teammate.repository.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamReplyServiceImpl implements TeamReplyService {

    private final TeamReplyRepository teamReplyRepository;

    private final TeamRepository teamRepository;

    // 댓글 등록 처리
    @Override
    public Long register(TeamReplyDTO teamReplyDTO) {
        TeamReply teamReply = dtoToEntity(teamReplyDTO);
        Team team = teamRepository.findById(teamReplyDTO.getTrTeam())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. idx=" + teamReplyDTO.getTrTeam()));
        teamReply.setTrTeam(teamRepository.save(team));
        teamReplyRepository.save(teamReply);
        return team.getIdx();
    }

    // 댓글 목록
    @Override
    public List<TeamReplyDTO> getList(Long idx) {
        List<TeamReply> result = teamReplyRepository.getTeamReply(Team.builder().idx(idx).build());

        return result.stream().map(teamReply -> {
            User user = teamReply.getTrUser();
            return entityToDTO(teamReply, user);
        }).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    @Override
    public void modify(TeamReplyDTO teamReplyDTO) {
        TeamReply teamReply = teamReplyRepository.getReferenceById(teamReplyDTO.getIdx());

        teamReply.changeContent(teamReplyDTO.getTrcontent());

        teamReplyRepository.save(teamReply);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void remove(TeamReplyDTO teamReplyDTO) {
        TeamReply teamReply = teamReplyRepository.getReferenceById(teamReplyDTO.getIdx());

        if(teamReplyDTO.isTrdelete() == true) {
            teamReply.changeDelete(false);
            teamReplyRepository.save(teamReply);
        }
    }
}
