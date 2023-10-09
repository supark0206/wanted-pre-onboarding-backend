package com.assignment.recurit.service;

import com.assignment.recurit.dto.request.RecruitmentRequest;

public interface RecruitmentService {
    public Long register(Long companyId, RecruitmentRequest recruitmentRequest);

    public Long update(Long companyId, Long recruitmentId, RecruitmentRequest recruitmentRequest);
}
