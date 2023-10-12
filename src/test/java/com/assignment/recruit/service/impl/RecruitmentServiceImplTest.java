package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.JoinCompanyRequest;
import com.assignment.recruit.dto.request.RecruitmentRequest;
import com.assignment.recruit.dto.response.RecruitmentListResponse;
import com.assignment.recruit.entity.Company;
import com.assignment.recruit.entity.Recruitment;
import com.assignment.recruit.exception.CustomException;
import com.assignment.recruit.exception.ErrorCode;
import com.assignment.recruit.repository.CompanyRepository;
import com.assignment.recruit.repository.RecruitmentRepository;
import com.assignment.recruit.service.CompanyService;
import com.assignment.recruit.service.RecruitmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class RecruitmentServiceImplTest {

    private static final JoinCompanyRequest joinCompanyRequest = JoinCompanyRequest.builder()
            .name("회사명")
            .email("test@test.com")
            .type("IT회사")
            .build();

    @Autowired
    RecruitmentService recruitmentService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;
    
    @Autowired
    RecruitmentRepository recruitmentRepository;

    @Test
    void 채용공고등록(){
        //given
        //회사
        Long saveCompanyId = companyService.join(joinCompanyRequest);

        Company saveCompany = companyRepository.findById(saveCompanyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

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
        Long saveCompanyId = companyService.join(joinCompanyRequest);

        Company saveCompany = companyRepository.findById(saveCompanyId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

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
        Long saveCompanyId = companyService.join(joinCompanyRequest);

        List<RecruitmentRequest> recruitmentRequestList = getRecruitmentRequestList();

        recruitmentRequestList.forEach(
                recruitmentRequest -> recruitmentService.register(saveCompanyId, recruitmentRequest)
        );

        // when
        Long recruitmentId = recruitmentRepository.findAll().get(0).getId();

        recruitmentService.remove(saveCompanyId, recruitmentId);
        List<Recruitment> findAll = recruitmentRepository.findAll();

        //then
        Assertions.assertThat(findAll.size()).isEqualTo(recruitmentRequestList.size()-1);
    }

    @Test
    void 채용공고목록(){

        //given
        Long saveCompanyId = companyService.join(joinCompanyRequest);

        List<RecruitmentRequest> recruitmentRequestList = getRecruitmentRequestList();

        recruitmentRequestList.forEach(
                recruitmentRequest -> recruitmentService.register(saveCompanyId, recruitmentRequest)
        );

        //0페이지 3사이즈
        Pageable pageable = PageRequest.of(0,3, Sort.by("id"));

        //when
        RecruitmentListResponse recruitmentListResponse = RecruitmentListResponse.recruitmentListResponseFrom(recruitmentService.recruitmentList(pageable));

        //then
        Assertions.assertThat(recruitmentListResponse.getTotalPages()).isEqualTo(1);
        Assertions.assertThat(recruitmentListResponse.getRecruitmentResponseList().size()).isEqualTo(3);

    }
    

    @Test
    void 채용공고검색(){
        //given
        Long saveCompanyId = companyService.join(joinCompanyRequest);

        List<RecruitmentRequest> recruitmentRequestList = getRecruitmentRequestList();

        recruitmentRequestList.forEach(
                recruitmentRequest -> recruitmentService.register(saveCompanyId, recruitmentRequest)
        );

        //0페이지 3사이즈
        Pageable pageable = PageRequest.of(0,3, Sort.by("id"));


        //when
        String search = "java";
        RecruitmentListResponse recruitmentListResponse = RecruitmentListResponse.recruitmentListResponseFrom(recruitmentService.searchRecruitmentList(pageable,search));


        //then
        Assertions.assertThat(recruitmentListResponse.getTotalPages()).isEqualTo(1);
        Assertions.assertThat(recruitmentListResponse.getRecruitmentResponseList().size()).isEqualTo(2);

    }

    //채용공고 리스트 생성
    private List<RecruitmentRequest> getRecruitmentRequestList() {
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

        return recruitmentRequestList;
    }


}