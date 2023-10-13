package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.RecruitmentRequest;
import com.assignment.recruit.dto.response.RecruitmentDetailResponse;
import com.assignment.recruit.dto.response.RecruitmentResponse;
import com.assignment.recruit.entity.Company;
import com.assignment.recruit.entity.Recruitment;
import com.assignment.recruit.exception.CustomException;
import com.assignment.recruit.exception.ErrorCode;
import com.assignment.recruit.repository.CompanyRepository;
import com.assignment.recruit.repository.RecruitmentRepository;
import com.assignment.recruit.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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

    @Transactional
    @Override
    public Long remove(Long companyId, Long recruitmentId) {

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        Recruitment recruitment = recruitmentRepository.findByIdAndCompany(recruitmentId,company).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        recruitmentRepository.deleteById(recruitment.getId());

        return recruitment.getId();
    }

    @Override
    public Page<Recruitment> recruitmentList(Pageable pageable) {
        return recruitmentRepository.findAll(pageable);
    }

    @Override
    public Page<Recruitment> searchRecruitmentList(Pageable pageable, String search) {
        return recruitmentRepository.searchRecruitment(pageable, search, search, search, search);
    }

    @Override
    public RecruitmentDetailResponse getRecruitmentDetail(Long companyId, Long recruitmentId) {

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        Recruitment recruitment = recruitmentRepository.findByIdAndCompany(recruitmentId,company).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        ArrayList<Recruitment> recruitmentList = recruitmentRepository.findByCompany(company);
        recruitmentList.remove(recruitment);

        return RecruitmentDetailResponse.from(recruitment,recruitmentList);
    }

}
