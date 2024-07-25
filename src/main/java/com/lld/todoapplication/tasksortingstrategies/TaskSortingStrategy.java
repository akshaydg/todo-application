package com.lld.todoapplication.tasksortingstrategies;

import com.lld.todoapplication.model.Task;

import java.util.List;

public interface TaskSortingStrategy {
    List<Task> sort(List<Task> tasks);
}

