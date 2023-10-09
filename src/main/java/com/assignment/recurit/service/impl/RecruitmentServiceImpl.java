package com.assignment.recurit.service.impl;

import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.dto.request.JoinUserRequest;
import com.assignment.recurit.dto.request.RegisterRecruitmentRequest;
import com.assignment.recurit.entity.Company;
import com.assignment.recurit.entity.Recruitment;
import com.assignment.recurit.entity.User;
import com.assignment.recurit.exception.CustomException;
import com.assignment.recurit.exception.ErrorCode;
import com.assignment.recurit.repository.CompanyRepository;
import com.assignment.recurit.repository.RecruitmentRepository;
import com.assignment.recurit.repository.UserRepository;
import com.assignment.recurit.service.RecruitmentService;
import com.assignment.recurit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RecruitmentServiceImpl implements RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    @Override
    public Long register(Long companyId, RegisterRecruitmentRequest registerRecruitmentRequest) {

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        return recruitmentRepository.save(
                Recruitment.builder()
                        .company(company)
                        .content(registerRecruitmentRequest.getContent())
                        .position(registerRecruitmentRequest.getPosition())
                        .reward(registerRecruitmentRequest.getReward())
                        .skill(registerRecruitmentRequest.getSkill())
                        .build()
        ).getId();
    }

}
