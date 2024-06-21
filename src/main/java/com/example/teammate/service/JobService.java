package com.example.teammate.service;


import com.example.teammate.dto.PageRequestDTO;
import com.example.teammate.dto.PageResultDTO;
import com.example.teammate.entity.Job;
import com.example.teammate.dto.JobDTO;
public interface JobService {

    // 게시글 목록 처리
    PageResultDTO<JobDTO, Job> getList(PageRequestDTO pageRequestDTO);


    default Job dtoToEntity(JobDTO dto) {
        Job job = Job.builder()
                .idx(dto.getIdx())
                .company(dto.getCompany())
                .jtitle(dto.getJtitle())
                .stack(dto.getStack())
                .place(dto.getPlace())
                .career(dto.getCareer())
                .education(dto.getEducation())
                .date(dto.getDate())
                .pageLink(dto.getPageLink())
                .build();
        return job;
    }

    default JobDTO entityToDTO(Job job) {
        JobDTO jobDTO = JobDTO.builder()
                .idx(job.getIdx())
                .company(job.getCompany())
                .jtitle(job.getJtitle())
                .stack(job.getStack())
                .place(job.getPlace())
                .career(job.getCareer())
                .education(job.getEducation())
                .date(job.getDate())
                .pageLink(job.getPageLink())
                .build();
        return jobDTO;
    }
}
