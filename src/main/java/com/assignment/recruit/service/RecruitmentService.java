package com.assignment.recruit.service;

import com.assignment.recruit.dto.request.RecruitmentRequest;
import com.assignment.recruit.dto.response.RecruitmentDetailResponse;
import com.assignment.recruit.entity.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
    public Long register(RecruitmentRequest recruitmentRequest);

    public Long update(Long recruitmentId, RecruitmentRequest recruitmentRequest);

    public Long remove(Long companyId,Long recruitmentId);

    public Page<Recruitment> recruitmentList(Pageable pageable);

    public Page<Recruitment> searchRecruitmentList(Pageable pageable, String search);

    public RecruitmentDetailResponse getRecruitmentDetail(Long companyId, Long recruitmentId);

}
