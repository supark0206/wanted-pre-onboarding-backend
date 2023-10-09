package com.assignment.recruit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentRequest {

    //포지션
    private String position;

    //채용보상금
    private String reward;

    //내용
    private String content;

    //스킬
    private String skill;
}