package com.assignment.recurit.controller;

import com.assignment.recurit.dto.commonResponse.ResultResponse;
import com.assignment.recurit.dto.request.JoinUserRequest;
import com.assignment.recurit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResultResponse> joinUser(@RequestBody JoinUserRequest joinUserRequest){
        Long id = userService.joinUser(joinUserRequest);

        return ResponseEntity.ok(new ResultResponse(id,"회원가입에 성공하였습니다."));
    }
}
