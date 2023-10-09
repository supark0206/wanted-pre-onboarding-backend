package com.assignment.recurit.service;

import com.assignment.recurit.dto.request.JoinCompanyRequest;

public interface CompanyService {
    public Long join(JoinCompanyRequest joinCompanyRequest);
}
