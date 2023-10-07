package com.assignment.recurit.entity;

import com.assignment.recurit.entity.BaseTimeEntity;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;


@Entity
@EnableJpaAuditing
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String name;

    private Integer age;

    public Long getId() {
        return id;
    }
}