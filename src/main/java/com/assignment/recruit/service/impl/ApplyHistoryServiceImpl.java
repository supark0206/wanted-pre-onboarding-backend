package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.SaveApplyHistoryRequest;
import com.assignment.recruit.entity.ApplyHistory;
import com.assignment.recruit.entity.Recruitment;
import com.assignment.recruit.entity.User;
import com.assignment.recruit.exception.CustomException;
import com.assignment.recruit.exception.ErrorCode;
import com.assignment.recruit.repository.ApplyHistoryRepository;
import com.assignment.recruit.repository.RecruitmentRepository;
import com.assignment.recruit.repository.UserRepository;
import com.assignment.recruit.service.ApplyHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApplyHistoryServiceImpl implements ApplyHistoryService {

    private final ApplyHistoryRepository applyHistoryRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Long save(SaveApplyHistoryRequest saveApplyHistoryRequest) {

        User user = userRepository.findById(saveApplyHistoryRequest.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        Recruitment recruitment = recruitmentRepository.findById(saveApplyHistoryRequest.getRecruitmentId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND)
        );

        if(applyHistoryRepository.findByRecruitmentAndUser(recruitment,user).isEmpty()){
            return applyHistoryRepository.save(
                    ApplyHistory.builder()
                            .recruitment(recruitment)
                            .user(user)
                            .build()
            ).getId();
        }else{
            throw new CustomException(ErrorCode.EXIST_APPLY_HISTORY);
        }

    }
}
