package com.assignment.recruit.controller;

import com.assignment.recruit.dto.commonResponse.ResultResponse;
import com.assignment.recruit.dto.request.RecruitmentRequest;
import com.assignment.recruit.dto.response.RecruitmentDetailResponse;
import com.assignment.recruit.dto.response.RecruitmentListResponse;
import com.assignment.recruit.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitment")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> register(@RequestBody RecruitmentRequest recruitmentRequest){
        Long id = recruitmentService.register(recruitmentRequest);

        return ResponseEntity.ok(new ResultResponse(id,"채용공고를 등록하였습니다."));
    }

    @PutMapping("/{recruitId}")
    public ResponseEntity<ResultResponse> update(@PathVariable Long recruitId, @RequestBody RecruitmentRequest recruitmentRequest){
        Long id = recruitmentService.update(recruitId, recruitmentRequest);

        return ResponseEntity.ok(new ResultResponse(id,"채용공고를 수정하였습니다."));
    }

    @DeleteMapping("/{recruitId}/{companyId}")
    public ResponseEntity<ResultResponse> remove(@PathVariable Long recruitId,@PathVariable Long companyId){
        Long id = recruitmentService.remove(companyId,recruitId);

        return ResponseEntity.ok(new ResultResponse(id,"채용공고를 삭제하였습니다.."));
    }

    @GetMapping("")
    public RecruitmentListResponse recruitmentList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(page == null) page = 1;

        Pageable pageable = PageRequest.of(page-1,size, Sort.by("id"));

        return RecruitmentListResponse.recruitmentListResponseFrom(recruitmentService.recruitmentList(pageable));
    }

    @GetMapping("/list")
    public RecruitmentListResponse searchRecruitmentList(@RequestParam(value="search") String search,
                                                         @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(page == null) page = 1;

        Pageable pageable = PageRequest.of(page-1,size, Sort.by("id"));

        return RecruitmentListResponse.recruitmentListResponseFrom(recruitmentService.searchRecruitmentList(pageable,search));
    }

    @GetMapping("/detail/{companyId}/{recruitId}")
    public RecruitmentDetailResponse getRecruitmentDetail(@PathVariable Long companyId, @PathVariable Long recruitId){
        return recruitmentService.getRecruitmentDetail(companyId,recruitId);
    }
}
