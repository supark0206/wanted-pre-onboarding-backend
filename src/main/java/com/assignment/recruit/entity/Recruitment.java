package com.assignment.recruit.entity;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@Entity
@EnableJpaAuditing
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Recruitment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    //포지션
    private String position;

    //채용보상금
    private String reward;

    //내용
    private String content;

    //스킬
    private String skill;
    

}
