package com.assignment.recruit.dto.response;

import com.assignment.recruit.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentListResponse {

    private List<RecruitmentResponse> recruitmentResponseList;
    private int totalPages;

    public static RecruitmentListResponse recruitmentListResponseFrom(Page<Recruitment> recruitmentPage) {
        List<RecruitmentResponse> recruitmentList =
                recruitmentPage.getContent()
                        .stream()
                        .map(RecruitmentResponse::from)
                        .collect(Collectors.toList());

        return RecruitmentListResponse.builder()
                .recruitmentResponseList(recruitmentList)
                .totalPages(recruitmentPage.getTotalPages())
                .build();
    }

}
