package com.sohan.student.dto;

import lombok.Data;

/**
 * POJO for holding Student related data
 */
@Data
public class EnrollStudentDTO {

    private String name;

    private String email;

    private String address;
}
