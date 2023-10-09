package com.assignment.recurit.service.impl;

import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.dto.request.RecruitmentRequest;
import com.assignment.recurit.entity.Company;
import com.assignment.recurit.entity.Recruitment;
import com.assignment.recurit.exception.CustomException;
import com.assignment.recurit.exception.ErrorCode;
import com.assignment.recurit.repository.CompanyRepository;
import com.assignment.recurit.repository.RecruitmentRepository;
import com.assignment.recurit.service.CompanyService;
import com.assignment.recurit.service.RecruitmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RecruitmentServiceImplTest {
    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;
    
    @Autowired
    RecruitmentRepository recruitmentRepository;

    private RecruitmentRequest recruitmentRequest;
    private JoinCompanyRequest joinCompanyRequest;
    
    @BeforeEach
    void beforeEach() {
        joinCompanyRequest = JoinCompanyRequest.builder()
                .name("회사명")
                .email("test@test.com")
                .type("IT회사")
                .build();
        
        recruitmentRequest = RecruitmentRequest.builder()
                .content("내용")
                .position("신입")
                .skill("java")
                .reward("100만원")
                .build();
    }


    @Test
    void 채용공고등록(){
        //given
        Long saveCompanyId = companyService.join(joinCompanyRequest);

        Company saveCompany = companyRepository.findById(saveCompanyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        // when
        Long saveId = recruitmentService.register(saveCompanyId, recruitmentRequest);

        Recruitment recruitment = recruitmentRepository.findById(saveId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        // then
        Assertions.assertThat(recruitmentRequest.getContent()).isEqualTo(recruitment.getContent());
        Assertions.assertThat(recruitmentRequest.getReward()).isEqualTo(recruitment.getReward());
        Assertions.assertThat(recruitmentRequest.getPosition()).isEqualTo(recruitment.getPosition());
        Assertions.assertThat(recruitmentRequest.getSkill()).isEqualTo(recruitment.getSkill());

        Assertions.assertThat(recruitment.getCompany()).isEqualTo(saveCompany);

    }

}