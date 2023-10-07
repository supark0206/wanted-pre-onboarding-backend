package com.assignment.recurit.service;

import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.dto.request.JoinUserRequest;

public interface UserService {
    public Long joinUser(JoinUserRequest joinUserRequest);
}
