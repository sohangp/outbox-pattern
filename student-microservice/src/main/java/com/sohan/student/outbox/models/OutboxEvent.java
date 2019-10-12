package com.sohan.student.outbox.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * POJO for holding the OutboxEvent to be published.
 *
 * @author Sohan
 */
@Data
@AllArgsConstructor
public class OutboxEvent {

    private Integer aggregateId;

    private String eventType;

    private JsonNode payload;
}
