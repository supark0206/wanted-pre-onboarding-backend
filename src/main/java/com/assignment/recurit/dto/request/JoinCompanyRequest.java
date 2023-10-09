package com.assignment.recurit.dto.request;

import com.assignment.recurit.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinCompanyRequest {

    private String email;

    private String name;

    private String type;

    public static Company toEntity(JoinCompanyRequest joinCompanyRequest){
        return Company.builder()
                .name(joinCompanyRequest.getName())
                .email(joinCompanyRequest.getEmail())
                .type(joinCompanyRequest.getType())
                .build();
    }

}
