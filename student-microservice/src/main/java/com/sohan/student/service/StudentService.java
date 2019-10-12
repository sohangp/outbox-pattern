package com.sohan.student.service;

import com.sohan.student.dto.EmailChangeDTO;
import com.sohan.student.dto.EnrollStudentDTO;
import com.sohan.student.dto.StudentDTO;

/**
 * Service interface that masks the caller from the implementation that fetches / acts on StudentDTO
 * related data.
 *
 * @author Sohan
 */
public interface StudentService {

    /**
     * Gets the Student Details for the given StudentId.
     *
     * @param studentId
     * @return StudentDTO
     */
    StudentDTO getStudent(Integer studentId) throws Exception;

    /**
     * Enrolls the Student.
     *
     * @param student
     * @return StudentDTO
     */
    StudentDTO enrollStudent(EnrollStudentDTO student) throws Exception;

    /**
     * Updates the Student Email for the given studentId.
     *
     * @param studentId
     * @param studentEmail
     * @return StudentDTO
     */
    StudentDTO updateStudentEmail(Integer studentId, EmailChangeDTO studentEmail) throws Exception;
}
