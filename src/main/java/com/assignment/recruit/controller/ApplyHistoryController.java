package com.assignment.recruit.controller;

import com.assignment.recruit.dto.commonResponse.ResultResponse;
import com.assignment.recruit.dto.request.SaveApplyHistoryRequest;
import com.assignment.recruit.service.ApplyHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/apply-history")
public class ApplyHistoryController {

    private final ApplyHistoryService applyHistoryService;

    @PostMapping
    public ResponseEntity<ResultResponse> save(@RequestBody SaveApplyHistoryRequest saveApplyHistoryRequest){
        Long id = applyHistoryService.save(saveApplyHistoryRequest);

        return ResponseEntity.ok(new ResultResponse(id,"채용공고 지원에 성공하였습니다."));
    }


}
