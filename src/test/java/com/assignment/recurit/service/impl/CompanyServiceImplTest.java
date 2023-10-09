package com.assignment.recurit.service.impl;

import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.entity.Company;
import com.assignment.recurit.exception.CustomException;
import com.assignment.recurit.exception.ErrorCode;
import com.assignment.recurit.repository.CompanyRepository;
import com.assignment.recurit.service.CompanyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CompanyServiceImplTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    private JoinCompanyRequest joinCompanyRequest;

    @BeforeEach
    void beforeEach() {
        joinCompanyRequest = JoinCompanyRequest.builder()
                        .name("회사명")
                        .email("test@test.com")
                        .type("IT회사")
                        .build();
    }

    @Test
    void 기업가입(){
        //given
        // when
        Long saveId = companyService.join(joinCompanyRequest);

        Company saveCompany = companyRepository.findById(saveId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        // then
        Assertions.assertThat(joinCompanyRequest.getEmail()).isEqualTo(saveCompany.getEmail());
        Assertions.assertThat(joinCompanyRequest.getName()).isEqualTo(saveCompany.getName());
        Assertions.assertThat(joinCompanyRequest.getType()).isEqualTo(saveCompany.getType());

    }
}