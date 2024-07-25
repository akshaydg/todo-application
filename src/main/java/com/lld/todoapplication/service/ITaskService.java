package com.lld.todoapplication.service;

import com.lld.todoapplication.model.Task;
import com.lld.todoapplication.model.TaskStatus;
import com.lld.todoapplication.tasksortingstrategies.TaskSortingStrategy;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public interface ITaskService {
    void addTask(String description, Date deadline, Set<String> tags, String title, Date startsAt);

    Optional<Task> getTask(String taskId);

    void modifyTask(Task task);

    void removeTask(String taskId);

    List<Task> listTasks(Predicate<Task> filter, TaskSortingStrategy sortingStrategy);

    List<Task> showTodoList(TaskSortingStrategy sortingStrategy);

    void markTaskCompleted(String taskId);

}
