package com.assignment.recruit.service;

import com.assignment.recruit.dto.request.JoinCompanyRequest;
import com.assignment.recruit.dto.request.SaveApplyHistoryRequest;

public interface ApplyHistoryService {
    Long save(SaveApplyHistoryRequest saveApplyHistoryRequest);
}
