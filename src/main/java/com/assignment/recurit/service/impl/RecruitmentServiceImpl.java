package com.assignment.recurit.service.impl;

import com.assignment.recurit.dto.request.RecruitmentRequest;
import com.assignment.recurit.entity.Company;
import com.assignment.recurit.entity.Recruitment;
import com.assignment.recurit.exception.CustomException;
import com.assignment.recurit.exception.ErrorCode;
import com.assignment.recurit.repository.CompanyRepository;
import com.assignment.recurit.repository.RecruitmentRepository;
import com.assignment.recurit.service.RecruitmentService;
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
