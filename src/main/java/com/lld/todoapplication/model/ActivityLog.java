package com.lld.todoapplication.model;


import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ActivityLog{

    private final String activityId;
    private final String entityId;
    private final String entityType;
    private final String entityStatus;
    private final Date timestamp;


    public ActivityLog(String entityId, String entityType, String entityStatus) {
        this.entityType = entityType;
        this.entityStatus = entityStatus;
        this.activityId = UUID.randomUUID().toString();
        this.entityId = entityId;
        this.timestamp = new Date();
    }
}

