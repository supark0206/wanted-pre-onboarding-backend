package com.assignment.recurit.controller;

import com.assignment.recurit.dto.commonResponse.ResultResponse;
import com.assignment.recurit.dto.request.RecruitmentRequest;
import com.assignment.recurit.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("/{companyId}")
    public ResponseEntity<ResultResponse> register(@PathVariable Long companyId, @RequestBody RecruitmentRequest recruitmentRequest){
        Long id = recruitmentService.register(companyId, recruitmentRequest);

        return ResponseEntity.ok(new ResultResponse(id,"채용공고를 등록하였습니다."));
    }
}
