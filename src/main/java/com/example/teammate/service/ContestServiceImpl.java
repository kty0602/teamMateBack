package com.example.teammate.service;

import com.example.teammate.dto.ContestDTO;
import com.example.teammate.dto.PageRequestDTO;
import com.example.teammate.dto.PageResultDTO;
import com.example.teammate.entity.Contest;
import com.example.teammate.repository.ContestRepository;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;

    @Override
    public PageResultDTO<ContestDTO, Contest> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());

        // Repository를 통해 데이터 조회
        Page<Contest> result = contestRepository.getContestList(pageable);

        // PageResultDTO를 생성하여 반환
        Function<Contest, ContestDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }


}
