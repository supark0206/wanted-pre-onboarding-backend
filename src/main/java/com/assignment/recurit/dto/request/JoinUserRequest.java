package com.assignment.recurit.dto.request;

import lombok.Data;

@Data
public class JoinUserRequest {

    private String email;

    private String name;

    private Integer age;
}
