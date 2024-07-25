package com.lld.todoapplication.repository;

import com.lld.todoapplication.model.Task;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface ITaskRepository {

    public abstract void addTask(Task task);

    public abstract Task getTask(String taskId);

    public abstract void updateTask(Task task);

    public abstract void removeTask(String taskId);

    public abstract List<Task> listTasks(Predicate<Task> filter);

}
