package com.example.teammate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import java.util.function.Function;
import com.example.teammate.dto.*;
import com.example.teammate.entity.*;
import com.example.teammate.repository.*;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamReplyRepository teamReplyRepository;

    // 게시글 등록 처리
    @Override
    public Long register(TeamDTO teamDTO) {
        Team team = dtoToEntity(teamDTO);
        teamRepository.save(team);
        return team.getIdx();
    }

    // 게시글 목록 처리
    @Override
    public PageResultDTO<TeamDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        Function<Object[], TeamDTO> fn = (en -> entityToDTO((Team) en[0], (User) en[1], (Long) en[2]));
        Page<Object[]> result = teamRepository.searchPage(
                pageRequestDTO.getBtype(),
                pageRequestDTO.getPageable(Sort.by("idx").descending()));

        return new PageResultDTO<>(result, fn);
    }

    // 게시글 조회 서비스
    @Override
    public TeamDTO get(Long idx) {
        Object result = teamRepository.getTeamByIdx(idx);
        Object[] arr = (Object[])result;
        return entityToDTO((Team)arr[0], (User)arr[1], (Long)arr[2]);
    }

    // 게시글 삭제 처리
    @Transactional
    @Override
    public void remove(TeamDTO teamDTO) {
        Team team = teamRepository.getReferenceById(teamDTO.getIdx());

        if(teamDTO.isTdelete() == true) {
            team.changeDelete(false);
            teamReplyRepository.deleteByIdx(teamDTO.getIdx());
            teamRepository.save(team);
        } else {
            System.out.println("Team is not marked for deletion");
        }
    }

    // 게시글 수정 처리
    @Transactional
    @Override
    public void modify(TeamDTO dto) {
        Team team = teamRepository.getReferenceById(dto.getIdx());

        if(dto.getTtitle() != null) {
            team.changeTitle(dto.getTtitle());
        }
        if(dto.getTcontent() != null) {
            team.changeContent(dto.getTcontent());
        }
        teamRepository.save(team);
    }

    // 게시글 모집 완료 변경 처리
    @Transactional
    @Override
    public void update(TeamDTO dto) {
        Team team = teamRepository.getReferenceById(dto.getIdx());

        if(dto.getState() == 0) {
            team.changeState(1);
            teamRepository.save(team);
        }
    }
}
