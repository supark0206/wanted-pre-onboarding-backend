package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.JoinUserRequest;
import com.assignment.recruit.entity.User;
import com.assignment.recruit.exception.CustomException;
import com.assignment.recruit.exception.ErrorCode;
import com.assignment.recruit.repository.UserRepository;
import com.assignment.recruit.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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