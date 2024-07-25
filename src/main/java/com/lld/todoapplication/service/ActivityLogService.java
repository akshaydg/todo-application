package com.lld.todoapplication.service;


import com.lld.todoapplication.model.ActivityLog;
import com.lld.todoapplication.repository.ActivityLogRepository;
import com.lld.todoapplication.repository.IActivityLogRepository;

import java.util.Date;
import java.util.List;

// todo interface for all services. activity service, third party service
public class ActivityLogService implements IActivityLogService {
    private final IActivityLogRepository activityLogRepository;

    public ActivityLogService(IActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public void logActivity(String entityId, String entityType, String entityStatus) {
        ActivityLog log = new ActivityLog(entityId, entityType,  entityStatus);
        activityLogRepository.addLog(log);
    }

    public List<ActivityLog> getActivityLogs() {
        return activityLogRepository.getLogs(null, null);
    }

    public List<ActivityLog> getActivityLogs(Date from, Date to) {
        return activityLogRepository.getLogs(from, to);
    }

}
