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
import com.assignment.recurit.service.CompanyService;
import com.assignment.recurit.service.RecruitmentService;
import com.assignment.recurit.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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

    private RegisterRecruitmentRequest registerRecruitmentRequest;
    private JoinCompanyRequest joinCompanyRequest;
    
    @BeforeEach
    void beforeEach() {
        joinCompanyRequest = JoinCompanyRequest.builder()
                .name("회사명")
                .email("test@test.com")
                .type("IT회사")
                .build();
        
        registerRecruitmentRequest = RegisterRecruitmentRequest.builder()
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
        Long saveId = recruitmentService.register(saveCompanyId,registerRecruitmentRequest);

        Recruitment recruitment = recruitmentRepository.findById(saveId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        // then
        Assertions.assertThat(registerRecruitmentRequest.getContent()).isEqualTo(recruitment.getContent());
        Assertions.assertThat(registerRecruitmentRequest.getReward()).isEqualTo(recruitment.getReward());
        Assertions.assertThat(registerRecruitmentRequest.getPosition()).isEqualTo(recruitment.getPosition());
        Assertions.assertThat(registerRecruitmentRequest.getSkill()).isEqualTo(recruitment.getSkill());

        Assertions.assertThat(recruitment.getCompany()).isEqualTo(saveCompany);

    }

}