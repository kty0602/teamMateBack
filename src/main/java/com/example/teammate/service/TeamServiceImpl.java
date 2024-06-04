package com.example.teammate.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import java.util.function.Function;
import com.example.teammate.dto.*;
import com.example.teammate.entity.*;
import com.example.teammate.repository.*;

import static java.nio.file.attribute.FileTime.from;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamReplyRepository teamReplyRepository;

    private final UserRepository userRepository;

    // 게시글 등록 처리
    @Override
    public Long register(TeamDTO teamDTO) {
        Team team = dtoToEntity(teamDTO);
        teamRepository.save(team);
        return team.getIdx();
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
        if(dto.getTskill() != null) {
            team.changeSkill(dto.getTskill());
        }
        teamRepository.save(team);
    }

    // 게시글 모집 완료/중 변경 처리
    @Transactional
    @Override
    public void update(TeamDTO dto) {
        Team team = teamRepository.getReferenceById(dto.getIdx());

        if(dto.getState() == 0) {
            team.changeState(1);
            teamRepository.save(team);
        }
        if(dto.getState() == 1) {
            team.changeState(0);
            teamRepository.save(team);
        }
    }

    // 게시글 목록 처리
    @Override
    public PageResultDTO<TeamDTO, Team> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("idx").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Team> result = teamRepository.findAll(booleanBuilder, pageable);

        Function<Team, TeamDTO> fn = (team -> {
            // user와 replyCount를 가져오는 로직을 작성합니다.
            User user = userRepository.findById(team.getTUser().getIdx()).orElseThrow(() -> new IllegalArgumentException("User not found"));

            Object result2 = teamRepository.getTeamByIdx(team.getIdx());
            Object[] arr = (Object[])result2;

            // entityToDTO 메소드를 호출합니다.
            return entityToDTO(team, user, (Long)arr[2]);
        });
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QTeam team = QTeam.team;

        String[] ktypeArray = requestDTO.getKtypeArray();
        String search = requestDTO.getSearch();

        BooleanExpression expression1 = team.idx.gt(0L);

        // 글이 삭제된 경우 포함시키지 않음
        booleanBuilder.and(expression1)
                .and(team.tdelete.ne(false));


        booleanBuilder.and(expression1);

        // 검색 조건이 없는 경우
        if ((ktypeArray == null || ktypeArray.length == 0) && (search == null || search.trim().length() == 0)) {
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (search != null) {
            String[] conditions = search.split(":");
            String field = conditions[0];
            String value = conditions[1];
            switch (field) {
                case "t":
                    conditionBuilder.or(team.ttitle.containsIgnoreCase(value));
                    break;
            }
        }
        if (ktypeArray != null) {
            for (String k : ktypeArray) {
                BooleanExpression ktypeMatch = Expressions.stringTemplate(
                        "FUNCTION('FIND_IN_SET', {0}, {1})", Expressions.constant(k), team.tskill).gt(String.valueOf(0));
                conditionBuilder.or(ktypeMatch);
            }
        }

        // 검색 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
