package com.lld.todoapplication.repository;

import com.lld.todoapplication.model.ActivityLog;

import java.util.Date;
import java.util.List;

public interface IActivityLogRepository {
    void addLog(ActivityLog log);
    List<ActivityLog> getLogs(Date from, Date to);
}
