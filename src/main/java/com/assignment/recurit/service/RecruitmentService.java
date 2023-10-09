package com.assignment.recurit.service;

import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.dto.request.RegisterRecruitmentRequest;

public interface RecruitmentService {
    public Long register(Long companyId, RegisterRecruitmentRequest registerRecruitmentRequest);
}
