package com.lld.todoapplication.repository;

import com.lld.todoapplication.model.ActivityLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityLogRepository implements IActivityLogRepository{
    private final List<ActivityLog> logs = new ArrayList<>();

    public void addLog(ActivityLog log) {
        logs.add(log);
    }

    public List<ActivityLog> getLogs(Date from, Date to) {
        if (from == null && to == null) {
            return new ArrayList<>(logs);
        }

        return logs.stream()
                .filter(log -> (from == null || !log.getTimestamp().before(from)) &&
                        (to == null || !log.getTimestamp().after(to)))
                .toList();
    }
}

