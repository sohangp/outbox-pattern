package com.sohan.student.controller;

import com.sohan.student.dto.EmailChangeDTO;
import com.sohan.student.dto.EnrollStudentDTO;
import com.sohan.student.dto.StudentDTO;
import com.sohan.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * This spring controller handles all the API calls made on the end point.
 * <p>
 * - /students/{studentId}: Gets the Student Details for the given StudentId.
 * <p/>
 *
 * @author Sohan
 */
@RestController
public class StudentController {

    /**
     * Handle to the service.
     */
    private final StudentService studentService;

    /**
     * Autowired constructor.
     *
     * @param studentService
     */
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Gets the Student Details for the given studentId.
     *
     * @param studentId
     * @return StudentDTO
     */
    @GetMapping(value = "/students/{studentId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StudentDTO getStudent(@PathVariable Integer studentId) throws Exception {
        return studentService.getStudent(studentId);
    }

    /**
     * Enrolls the Student.
     *
     * @param student
     * @return StudentDTO
     */
    @PostMapping(value = "/students/~/enroll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StudentDTO enrollStudent(@RequestBody EnrollStudentDTO student) throws Exception {
        return studentService.enrollStudent(student);
    }

    /**
     * Updates the Student Email for the given studentId.
     *
     * @param studentId
     * @param studentEmail
     * @return StudentDTO
     */
    @PutMapping(value = "/students/{studentId}/update-email", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public StudentDTO updateStudentEmail(@PathVariable Integer studentId, @RequestBody EmailChangeDTO studentEmail) throws Exception {
        return studentService.updateStudentEmail(studentId, studentEmail);
    }
}
