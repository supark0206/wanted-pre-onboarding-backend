package com.assignment.recruit.service.impl;

import com.assignment.recruit.dto.request.SaveApplyHistoryRequest;
import com.assignment.recruit.entity.ApplyHistory;
import com.assignment.recruit.entity.Company;
import com.assignment.recruit.entity.Recruitment;
import com.assignment.recruit.entity.User;
import com.assignment.recruit.exception.CustomException;
import com.assignment.recruit.exception.ErrorCode;
import com.assignment.recruit.repository.ApplyHistoryRepository;
import com.assignment.recruit.repository.CompanyRepository;
import com.assignment.recruit.repository.RecruitmentRepository;
import com.assignment.recruit.repository.UserRepository;
import com.assignment.recruit.service.ApplyHistoryService;
import com.assignment.recruit.service.CompanyService;
import com.assignment.recruit.service.RecruitmentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ApplyHistoryServiceImplTest {


    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    RecruitmentRepository recruitmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplyHistoryRepository applyHistoryRepository;

    @Autowired
    ApplyHistoryService applyHistoryService;

    private final User user = User.builder()
            .id(1L)
            .name("테스트사용자명")
            .age(19)
            .email("test@user.com")
            .build();

    private final Company company = Company.builder()
            .id(1L)
            .name("테스트회사명")
            .build();

    private final Recruitment recruitment = Recruitment.builder()
            .id(1L)
            .skill("자바")
            .position("포지션")
            .company(company)
            .build();


    @Test
    void 채용공고_지원하기() {
        //given
        companyRepository.save(company);
        User saveUser = userRepository.save(user);
        Recruitment saveRecruitment = recruitmentRepository.save(recruitment);

        //when
        Long saveId = applyHistoryService.save(
                SaveApplyHistoryRequest.builder()
                        .recruitmentId(saveRecruitment.getId())
                        .userId(saveUser.getId())
                        .build()
        );

        ApplyHistory applyHistory = applyHistoryRepository.findById(saveId).get();

        //then
        Assertions.assertThat(applyHistory.getUser()).isEqualTo(saveUser);
        Assertions.assertThat(applyHistory.getRecruitment()).isEqualTo(saveRecruitment);
    }

    @Test
    void 채용공고_중복지원() {
        //given
        companyRepository.save(company);
        User saveUser = userRepository.save(user);
        Recruitment saveRecruitment = recruitmentRepository.save(recruitment);

        //when
        applyHistoryService.save(
                SaveApplyHistoryRequest.builder()
                        .recruitmentId(saveRecruitment.getId())
                        .userId(saveUser.getId())
                        .build()
        );

        //then
        assertThrows(CustomException.class, () -> {
            applyHistoryService.save(
                    SaveApplyHistoryRequest.builder()
                            .recruitmentId(saveRecruitment.getId())
                            .userId(saveUser.getId())
                            .build()
            );
        });
    }
}