package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.RecruitmentRequest;
import com.assignment.recruit.entity.Company;
import com.assignment.recruit.entity.Recruitment;
import com.assignment.recruit.exception.CustomException;
import com.assignment.recruit.exception.ErrorCode;
import com.assignment.recruit.repository.CompanyRepository;
import com.assignment.recruit.repository.RecruitmentRepository;
import com.assignment.recruit.service.RecruitmentService;
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
    public Long register(Long companyId, RecruitmentRequest recruitmentRequest) {

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        return recruitmentRepository.save(
                Recruitment.builder()
                        .company(company)
                        .content(recruitmentRequest.getContent())
                        .position(recruitmentRequest.getPosition())
                        .reward(recruitmentRequest.getReward())
                        .skill(recruitmentRequest.getSkill())
                        .build()
        ).getId();
    }

    @Transactional
    @Override
    public Long update(Long companyId, Long recruitmentId, RecruitmentRequest recruitmentRequest) {

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        Recruitment recruitment = recruitmentRepository.findByIdAndCompany(recruitmentId,company).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        return recruitmentRepository.save(
                Recruitment.builder()
                        .id(recruitment.getId())
                        .company(company)
                        .content(recruitmentRequest.getContent())
                        .position(recruitmentRequest.getPosition())
                        .reward(recruitmentRequest.getReward())
                        .skill(recruitmentRequest.getSkill())
                        .build()
        ).getId();
    }

}