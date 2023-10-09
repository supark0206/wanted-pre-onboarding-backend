package com.assignment.recruit.service;

import com.assignment.recruit.dto.request.JoinCompanyRequest;

public interface CompanyService {
    public Long join(JoinCompanyRequest joinCompanyRequest);
}
