package com.example.teammate.service;

import com.example.teammate.dto.PageRequestDTO;
import com.example.teammate.dto.PageResultDTO;
import com.example.teammate.entity.Contest;
import com.example.teammate.dto.ContestDTO;
public interface ContestService {

    // 게시글 목록 처리
    PageResultDTO<ContestDTO, Contest> getList(PageRequestDTO pageRequestDTO);

    default Contest dtoToEntity(ContestDTO dto) {
        Contest contest = Contest.builder()
                .idx(dto.getIdx())
                .ctitle(dto.getCtitle())
                .pageLink(dto.getPageLink())
                .host(dto.getHost())
                .contestant(dto.getContestant())
                .joinDate(dto.getJoinDate())
                .reviewDate(dto.getReviewDate())
                .day(dto.getDay())
                .status(dto.getStatus())
                .build();
        return contest;
    }

    default ContestDTO entityToDTO(Contest contest) {
        ContestDTO contestDTO = ContestDTO.builder()
                .idx(contest.getIdx())
                .ctitle(contest.getCtitle())
                .pageLink(contest.getPageLink())
                .host(contest.getHost())
                .contestant(contest.getContestant())
                .joinDate(contest.getJoinDate())
                .reviewDate(contest.getReviewDate())
                .day(contest.getDay())
                .status(contest.getStatus())
                .build();
        return contestDTO;
    }


}
