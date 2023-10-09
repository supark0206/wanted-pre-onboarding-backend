package com.assignment.recurit.service.impl;

import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.dto.request.JoinUserRequest;
import com.assignment.recurit.entity.Company;
import com.assignment.recurit.entity.User;
import com.assignment.recurit.exception.CustomException;
import com.assignment.recurit.exception.ErrorCode;
import com.assignment.recurit.repository.UserRepository;
import com.assignment.recurit.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private JoinUserRequest joinUserRequest;

    @BeforeEach
    void beforeEach() {
        joinUserRequest = JoinUserRequest.builder()
                .name("회원명")
                .age(21)
                .email("member@test.com")
                .build();
    }


    @Test
    void 회원가입(){
        //given
        // when
        Long saveId = userService.join(joinUserRequest);

        User saveUser = userRepository.findById(saveId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        // then
        Assertions.assertThat(joinUserRequest.getEmail()).isEqualTo(saveUser.getEmail());
        Assertions.assertThat(joinUserRequest.getName()).isEqualTo(saveUser.getName());
        Assertions.assertThat(joinUserRequest.getAge()).isEqualTo(saveUser.getAge());

    }

}