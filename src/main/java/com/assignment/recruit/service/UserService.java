package com.assignment.recruit.service;

import com.assignment.recruit.dto.request.JoinUserRequest;

public interface UserService {
    public Long join(JoinUserRequest joinUserRequest);
}
