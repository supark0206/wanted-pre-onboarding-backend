package com.assignment.recurit.service;

import com.assignment.recurit.dto.commonResponse.ResultResponse;
import com.assignment.recurit.dto.request.JoinCompanyRequest;
import org.springframework.http.ResponseEntity;

public interface CompanyService {
    public Long joinCompany(JoinCompanyRequest joinCompanyRequest);
}
