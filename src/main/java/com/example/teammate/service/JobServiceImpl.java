package com.example.teammate.service;

import com.example.teammate.repository.JobRepository;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.*;
import com.example.teammate.entity.*;
import com.example.teammate.dto.*;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;

    private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QJob qJob = QJob.job;

        String rtype = pageRequestDTO.getRtype();
        String stype = pageRequestDTO.getStype();

        BooleanExpression expression1 = qJob.idx.gt(0);
        booleanBuilder.and(expression1);

        // 검색 조건 타입
        BooleanExpression rtypeExpression = null;
        if (rtype != null) {
            String[] rtypes = rtype.split(",");
            for (String r : rtypes) {
                BooleanExpression rtypeCondition = qJob.place.contains(r.trim());
                if (rtypeExpression == null) {
                    rtypeExpression = rtypeCondition;
                } else {
                    rtypeExpression = rtypeExpression.or(rtypeCondition);
                }
            }
        }

        BooleanExpression stypeExpression = null;
        if (stype != null) {
            String[] stypes = stype.split(",");
            for (String s : stypes) {
                BooleanExpression stypeCondition = qJob.stack.contains(s.trim());
                if (stypeExpression == null) {
                    stypeExpression = stypeCondition;
                } else {
                    stypeExpression = stypeExpression.or(stypeCondition);
                }
            }
        }

        if (rtypeExpression != null) {
            booleanBuilder.and(rtypeExpression);
        }
        if (stypeExpression != null) {
            booleanBuilder.and(stypeExpression);
        }

        return booleanBuilder;
    }

    @Override
    public PageResultDTO<JobDTO, Job> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("idx").descending());
        BooleanBuilder booleanBuilder = getSearch(pageRequestDTO);
        Page<Job> result = jobRepository.findAll(booleanBuilder, pageable);
        Function<Job, JobDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }
}
