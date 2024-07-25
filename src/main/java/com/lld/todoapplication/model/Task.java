package com.lld.todoapplication.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class Task{
    private String id;
    private String description;
    private Date deadline;
    private Set<String> tags;
    private TaskStatus status;
    private Date completedAt;
    private Date createdAt;
    private String title;
    private Date startsAt;
    private String userId; // can be added later


    private Task(String id, String description, Date deadline, Set<String> tags, TaskStatus status,
                 Date completedAt, Date createdAt, String title, Date startsAt, String userId) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.tags = tags;
        this.status = status;
        this.completedAt = completedAt;
        this.createdAt = createdAt;
        this.title = title;
        this.startsAt = startsAt;
        this.userId = userId;
    }

    public static class TaskBuilder {

        // Override the build method to add custom validation
        public Task build() {
            // Custom validation logic
            if (deadline != null && deadline.before(new Date())) {
                throw new IllegalArgumentException("Deadline cannot be in the past");
            }
            if (status == TaskStatus.COMPLETED && completedAt == null) {
                throw new IllegalArgumentException("Completed tasks must have a completion date");
            }
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty");
            }
            // Additional validations can be added as needed

            return new Task(id, description, deadline, tags, status, completedAt, createdAt, title, startsAt, userId);
        }
    }
}

