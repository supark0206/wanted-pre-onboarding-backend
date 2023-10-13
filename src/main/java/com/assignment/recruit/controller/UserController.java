package com.assignment.recruit.controller;

import com.assignment.recruit.dto.commonResponse.ResultResponse;
import com.assignment.recruit.dto.request.JoinUserRequest;
import com.assignment.recruit.service.UserService;
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
    public ResponseEntity<ResultResponse> join(@RequestBody JoinUserRequest joinUserRequest){
        Long id = userService.join(joinUserRequest);

        return ResponseEntity.ok(new ResultResponse(id,"회원가입에 성공하였습니다."));
    }
}
