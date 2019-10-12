package com.sohan.student.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sohan.student.dao.entities.StudentEntity;
import com.sohan.student.outbox.models.OutboxEvent;

/**
 * Utility class to help the service in building event payloads.
 *
 * @author Sohan
 */
public class EventUtils {

    /**
     * Create the event object to be published when new student is enrolled.
     *
     * @param studentEntity
     * @return OutboxEvent
     */
    public static OutboxEvent createEnrollEvent(StudentEntity studentEntity) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(studentEntity, JsonNode.class);

        return new OutboxEvent(
                studentEntity.getStudentId(),
                "STUDENT_ENROLLED",
                jsonNode
        );
    }

    /**
     * Create the event object to be published when student email is changed.
     *
     * @param studentEntity
     * @return OutboxEvent
     */
    public static OutboxEvent createUpdateEmailEvent(StudentEntity studentEntity) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode()
                .put("studentId", studentEntity.getStudentId())
                .put("email",studentEntity.getEmail());

        return new OutboxEvent(
                studentEntity.getStudentId(),
                "STUDENT_EMAIL_CHANGED",
                jsonNode
        );
    }
}
