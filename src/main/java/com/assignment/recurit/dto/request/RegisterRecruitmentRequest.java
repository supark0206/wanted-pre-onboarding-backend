package com.assignment.recurit.dto.request;

import com.assignment.recurit.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRecruitmentRequest {

    //포지션
    private String position;

    //채용보상금
    private String reward;

    //내용
    private String content;

    //스킬
    private String skill;
}
