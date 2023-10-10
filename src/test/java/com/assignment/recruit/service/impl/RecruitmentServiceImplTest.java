package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.JoinCompanyRequest;
import com.assignment.recruit.dto.request.RecruitmentRequest;
import com.assignment.recruit.entity.Company;
import com.assignment.recruit.entity.Recruitment;
import com.assignment.recruit.exception.CustomException;
import com.assignment.recruit.exception.ErrorCode;
import com.assignment.recruit.repository.CompanyRepository;
import com.assignment.recruit.repository.RecruitmentRepository;
import com.assignment.recruit.service.CompanyService;
import com.assignment.recruit.service.RecruitmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    private Company saveCompany;
    private Long saveCompanyId;
    
    @BeforeEach
    void beforeEach() {
        JoinCompanyRequest joinCompanyRequest = JoinCompanyRequest.builder()
                .name("회사명")
                .email("test@test.com")
                .type("IT회사")
                .build();

        //회사
        saveCompanyId = companyService.join(joinCompanyRequest);

        saveCompany = companyRepository.findById(saveCompanyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );


    }


    @Test
    void 채용공고등록(){
        //given
        RecruitmentRequest recruitmentRequest = RecruitmentRequest.builder()
                .content("내용")
                .position("신입")
                .skill("java")
                .reward("100만원")
                .build();

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


    @Test
    void 채용공고수정(){

        //given
        RecruitmentRequest recruitmentRequest = RecruitmentRequest.builder()
                .content("내용")
                .position("신입")
                .skill("java")
                .reward("100만원")
                .build();

        RecruitmentRequest recruitmentUpdateRequest = RecruitmentRequest.builder()
                .content("내용 수정")
                .position("신입 수정")
                .skill("java 수정")
                .reward("100만원 수정")
                .build();

        Long saveId = recruitmentService.register(saveCompanyId, recruitmentRequest);

        // when
        Long updateId = recruitmentService.update(saveCompanyId, saveId, recruitmentUpdateRequest);

        Recruitment recruitmentUpdate = recruitmentRepository.findById(updateId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );


        // then
        Assertions.assertThat(recruitmentUpdateRequest.getContent()).isEqualTo(recruitmentUpdate.getContent());
        Assertions.assertThat(recruitmentUpdateRequest.getReward()).isEqualTo(recruitmentUpdate.getReward());
        Assertions.assertThat(recruitmentUpdateRequest.getPosition()).isEqualTo(recruitmentUpdate.getPosition());
        Assertions.assertThat(recruitmentUpdateRequest.getSkill()).isEqualTo(recruitmentUpdate.getSkill());

        Assertions.assertThat(recruitmentUpdate.getCompany()).isEqualTo(saveCompany);

    }

    @Test
    void 채용공고삭제(){
        //given
        List<RecruitmentRequest> recruitmentRequestList = new ArrayList<>();

        //채용공고리스트
        recruitmentRequestList.add(
                RecruitmentRequest.builder()
                        .content("내용").position("신입1").skill("java").reward("100만원")
                        .build()
        );
        recruitmentRequestList.add(
                RecruitmentRequest.builder()
                        .content("내용").position("신입2").skill("java").reward("100만원")
                        .build()
        );
        recruitmentRequestList.add(
                RecruitmentRequest.builder()
                        .content("내용").position("신입3").skill("python").reward("100만원")
                        .build()
        );


        recruitmentRequestList.forEach(
                recruitmentRequest -> recruitmentService.register(saveCompanyId, recruitmentRequest)
        );

        // when
        recruitmentService.remove(saveCompanyId, 1L);
        List<Recruitment> findAll = recruitmentRepository.findAll();

        //then
        Assertions.assertThat(findAll.size()).isEqualTo(recruitmentRequestList.size()-1);
    }

    @Test
    void 채용공고목록(){

        //given
        List<RecruitmentRequest> recruitmentRequestList = new ArrayList<>();

        //채용공고리스트
        recruitmentRequestList.add(
                RecruitmentRequest.builder()
                        .content("내용")
                        .position("신입1")
                        .skill("java")
                        .reward("100만원")
                        .build()
        );
        recruitmentRequestList.add(
                RecruitmentRequest.builder()
                        .content("내용")
                        .position("신입2")
                        .skill("java")
                        .reward("100만원")
                        .build()
        );
        recruitmentRequestList.add(
                RecruitmentRequest.builder()
                        .content("내용")
                        .position("신입3")
                        .skill("python")
                        .reward("100만원")
                        .build()
        );
    }

}