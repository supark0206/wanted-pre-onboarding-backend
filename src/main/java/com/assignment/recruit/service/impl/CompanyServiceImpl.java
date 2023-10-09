package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.JoinCompanyRequest;
import com.assignment.recruit.entity.Company;
import com.assignment.recruit.repository.CompanyRepository;
import com.assignment.recruit.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    @Override
    public Long join(JoinCompanyRequest joinCompanyRequest) {

        return companyRepository.save(
                Company.builder()
                        .name(joinCompanyRequest.getName())
                        .email(joinCompanyRequest.getEmail())
                        .type(joinCompanyRequest.getType())
                        .build()
        ).getId();
    }
}
