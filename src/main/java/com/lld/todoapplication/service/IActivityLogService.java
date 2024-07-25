package com.lld.todoapplication.service;

import com.lld.todoapplication.model.ActivityLog;

import java.util.Date;
import java.util.List;

public interface IActivityLogService {
    void logActivity(String entityId, String entityType, String entityStatus);

    List<ActivityLog> getActivityLogs();

    List<ActivityLog> getActivityLogs(Date from, Date to);
}
