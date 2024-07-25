package com.lld.todoapplication.repository;

import com.lld.todoapplication.model.Task;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class TaskRepository implements ITaskRepository {
    private final AtomicInteger currentId;
    private final Map<String, Task> tasks;


    public TaskRepository() {
        tasks = new HashMap<>();
        currentId = new AtomicInteger(1);
    }

    public void addTask(Task task) {
        task.setId(String.valueOf(currentId.getAndIncrement()));
        tasks.put(task.getId(), task);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(String taskId) {
        tasks.remove(taskId);
    }

    public List<Task> listTasks(Predicate<Task> filter) {
        return tasks.values().stream()
                .filter(filter)
                .toList();
    }
}

