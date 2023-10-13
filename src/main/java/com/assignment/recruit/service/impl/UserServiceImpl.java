package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.JoinUserRequest;
import com.assignment.recruit.entity.User;
import com.assignment.recruit.repository.UserRepository;
import com.assignment.recruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public Long join(JoinUserRequest joinUserRequest) {
        return userRepository.save(
                User.builder()
                        .email(joinUserRequest.getEmail())
                        .name(joinUserRequest.getName())
                        .age(joinUserRequest.getAge())
                        .build()
        ).getId();
    }
}
