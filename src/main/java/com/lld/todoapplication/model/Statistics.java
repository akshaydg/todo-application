package com.lld.todoapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Statistics {
    private int tasksAdded;
    private int tasksCompleted;
    private int tasksSpilledOver;

    public Statistics(int tasksAdded, int tasksCompleted, int tasksSpilledOver) {
        this.tasksAdded = tasksAdded;
        this.tasksCompleted = tasksCompleted;
        this.tasksSpilledOver = tasksSpilledOver;
    }
}
