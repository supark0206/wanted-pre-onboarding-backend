package com.assignment.recurit.controller;

import com.assignment.recurit.dto.commonResponse.ResultResponse;
import com.assignment.recurit.dto.request.JoinCompanyRequest;
import com.assignment.recurit.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<ResultResponse> join(@RequestBody JoinCompanyRequest joinCompanyRequest){
        Long id = companyService.join(joinCompanyRequest);

        return ResponseEntity.ok(new ResultResponse(id,"기업회원 가입 성공하였습니다."));
    }
}
