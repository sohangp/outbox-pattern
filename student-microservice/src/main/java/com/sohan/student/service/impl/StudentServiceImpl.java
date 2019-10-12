package com.sohan.student.service.impl;

import com.sohan.student.dao.StudentRepository;
import com.sohan.student.dao.entities.StudentEntity;
import com.sohan.student.dto.EmailChangeDTO;
import com.sohan.student.dto.EnrollStudentDTO;
import com.sohan.student.dto.StudentDTO;
import com.sohan.student.dto.StudentMapper;
import com.sohan.student.outbox.EventPublisher;
import com.sohan.student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Service Implementation that fetches / acts on StudentDTO related data.
 *
 * @author Sohan
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    /**
     * Handle to the Data Access Layer.
     */
    private final StudentRepository studentRepository;

    /**
     * Handle to the Outbox Eventing framework.
     */
    private final EventPublisher eventPublisher;

    /**
     * Autowired constructor.
     *
     * @param studentRepository
     * @param eventPublisher
     */
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, EventPublisher eventPublisher) {
        this.studentRepository = studentRepository;
        this.eventPublisher=eventPublisher;
    }

    /**
     * Gets the Student Details for the given StudentId.
     *
     * @return StudentDTO
     */
    @Override
    public StudentDTO getStudent(Integer studentId) throws Exception {
        log.info("Fetching Student details for StudentId: {}", studentId);

        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        StudentDTO studentDTO = null;

        if (studentEntity.isPresent()) {
            studentDTO = StudentMapper.INSTANCE.studentEntityToDTO(studentEntity.get());
        } else {
            throw new Exception("Student not found");
        }

        return studentDTO;
    }

    /**
     * Updates the Student Email for the given studentId.
     *
     * @param student
     * @return StudentDTO
     */
    @Override
    @Transactional
    public StudentDTO enrollStudent(EnrollStudentDTO student) throws Exception {
        log.info("Enroll Student details for StudentId: {}", student.getName());

        StudentEntity studentEntity = StudentMapper.INSTANCE.studentDTOToEntity(student);
        studentRepository.save(studentEntity);

        //Publish the event
        eventPublisher.fire(EventUtils.createEnrollEvent(studentEntity));

        return StudentMapper.INSTANCE.studentEntityToDTO(studentEntity);
    }

    /**
     * Updates the Student Email for the given studentId.
     *
     * @param studentId
     * @param studentEmail
     * @return StudentDTO
     */
    @Override
    @Transactional
    public StudentDTO updateStudentEmail(Integer studentId, EmailChangeDTO studentEmail) throws Exception {
        log.info("Update Email to '{}' for StudentId: {}", studentEmail.getEmail(),  studentId);

        StudentEntity studentEntity = studentRepository.getOne(studentId);
        studentEntity.setEmail(studentEmail.getEmail());
        studentRepository.save(studentEntity);

        //Publish the event
        eventPublisher.fire(EventUtils.createUpdateEmailEvent(studentEntity));

        return StudentMapper.INSTANCE.studentEntityToDTO(studentEntity);
    }
}
