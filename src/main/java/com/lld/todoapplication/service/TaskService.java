package com.lld.todoapplication.service;

import com.lld.todoapplication.model.EntityType;
import com.lld.todoapplication.model.Task;
import com.lld.todoapplication.model.TaskStatus;
import com.lld.todoapplication.repository.ITaskRepository;
import com.lld.todoapplication.tasksortingstrategies.TaskSortingStrategy;

import java.util.*;
import java.util.function.Predicate;


public class TaskService implements ITaskService{
    private final ITaskRepository taskRepository;
    private final IActivityLogService activityLogService;

    public TaskService(ITaskRepository taskRepository, IActivityLogService activityLogService) {
        this.taskRepository = taskRepository;
        this.activityLogService = activityLogService;
    }

    public void addTask(String description, Date deadline, Set<String> tags, String title, Date startsAt) {
        Task task = Task.builder()
                .description(description)
                .deadline(deadline)
                .tags(tags)
                .status(TaskStatus.CREATED)
                .createdAt(new Date())
                .title(title)
                .startsAt(startsAt)
                .build();
        taskRepository.addTask(task);
        activityLogService.logActivity(task.getId(), EntityType.TASK.toString(), TaskStatus.CREATED.toString());
    }

    public Optional<Task> getTask(String taskId) {
        return Optional.ofNullable(taskRepository.getTask(taskId));
    }

    public void modifyTask(Task task) {
        taskRepository.updateTask(task);
        activityLogService.logActivity(task.getId(), EntityType.TASK.toString(), task.getStatus().toString());
    }

    public void removeTask(String taskId) {
        taskRepository.removeTask(taskId);
        activityLogService.logActivity(taskId, EntityType.TASK.toString(), TaskStatus.DELETED.toString());    }

    public List<Task> listTasks(Predicate<Task> filter, TaskSortingStrategy sortingStrategy) {
        List<Task> tasks = taskRepository.listTasks(filter);
        return sortingStrategy.sort(tasks);
    }

    public List<Task> showTodoList(TaskSortingStrategy sortingStrategy) {
        return listTasks(
                task -> List.of(TaskStatus.CREATED, TaskStatus.IN_PROGRESS).contains(task.getStatus()),
                sortingStrategy);
    }

    public void markTaskCompleted(String taskId) {
        Task task = taskRepository.getTask(taskId);
        Date now = new Date();

        if (task.getDeadline() != null && task.getDeadline().before(now)) {
            task.setCompletedAt(now);
            task.setStatus(TaskStatus.DELAYED);
        } else {
            task.setCompletedAt(now);
            task.setStatus(TaskStatus.COMPLETED);
        }

        modifyTask(task);
    }

}