package com.assignment.recurit.service.impl;

import com.assignment.recurit.dto.commonResponse.ResultResponse;
import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.entity.Company;
import com.assignment.recurit.repository.CompanyRepository;
import com.assignment.recurit.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    @Override
    public Long joinCompany(JoinCompanyRequest joinCompanyRequest) {

        return companyRepository.save(
                Company.builder()
                        .name(joinCompanyRequest.getName())
                        .email(joinCompanyRequest.getEmail())
                        .type(joinCompanyRequest.getType())
                        .build()
        ).getId();
    }
}
