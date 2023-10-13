package com.assignment.recruit.dto.response;

import com.assignment.recruit.entity.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitmentDetailResponse {

    private Long id;

    private String companyName;

    private String position;

    private String skill;

    private String reward;

    private String content;

    private ArrayList<Long> anotherList;

    public static RecruitmentDetailResponse from(Recruitment recruitment,ArrayList<Recruitment> recruitmentList){
        return RecruitmentDetailResponse.builder()
                .id(recruitment.getId())
                .companyName(recruitment.getCompany().getName())
                .position(recruitment.getPosition())
                .skill(recruitment.getSkill())
                .reward(recruitment.getReward())
                .content(recruitment.getContent())
                .anotherList(recruitmentList.stream()
                        .map(Recruitment::getId)
                        .collect(Collectors.toCollection(ArrayList::new))
                )
                .build();
    }

}
