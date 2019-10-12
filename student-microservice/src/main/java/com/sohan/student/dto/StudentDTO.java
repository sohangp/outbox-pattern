package com.sohan.student.dto;

import lombok.Data;

/**
 * POJO for holding Student related data
 */
@Data
public class StudentDTO {

    private Integer studentId;

    private String name;

    private String email;

    private String address;
}
