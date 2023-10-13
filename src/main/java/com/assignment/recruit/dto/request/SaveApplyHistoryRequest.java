package com.assignment.recruit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveApplyHistoryRequest {
    private Long recruitmentId;
    private Long userId;
}
