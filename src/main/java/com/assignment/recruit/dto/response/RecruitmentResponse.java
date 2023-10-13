package com.assignment.recruit.dto.response;

import com.assignment.recruit.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentResponse {

    private Long id;

    private String companyName;

    private String position;

    private String skill;

    private String reward;

    public static RecruitmentResponse from(Recruitment recruitment){
        return RecruitmentResponse.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompany().getName())
                .position(recruitment.getPosition())
                .skill(recruitment.getSkill())
                .reward(recruitment.getReward())
                .build();
    }

}
