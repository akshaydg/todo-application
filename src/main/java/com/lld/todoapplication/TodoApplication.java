package com.lld.todoapplication;

import com.lld.todoapplication.exception.TaskNotFoundException;
import com.lld.todoapplication.model.ActivityLog;
import com.lld.todoapplication.model.Statistics;
import com.lld.todoapplication.model.Task;
import com.lld.todoapplication.repository.ActivityLogRepository;
import com.lld.todoapplication.repository.IActivityLogRepository;
import com.lld.todoapplication.repository.ITaskRepository;
import com.lld.todoapplication.repository.TaskRepository;
import com.lld.todoapplication.service.ActivityLogService;
import com.lld.todoapplication.service.StatisticsService;
import com.lld.todoapplication.service.TaskService;
import com.lld.todoapplication.tasksortingstrategies.DeadlineDescendingSortingStrategy;
import com.lld.todoapplication.tasksortingstrategies.TaskSortingStrategy;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {


        try {
            ITaskRepository taskRepository = new TaskRepository();
            IActivityLogRepository activityLogRepository = new ActivityLogRepository();

            ActivityLogService activityLogService = new ActivityLogService(activityLogRepository);
            TaskService taskService = new TaskService(taskRepository, activityLogService);
            StatisticsService statisticsService = new StatisticsService(taskRepository);

            taskService.addTask("Task 1 description", new Date(System.currentTimeMillis() + 86400000), new HashSet<>(Arrays.asList("work", "urgent")), "officeWork", new Date());
            taskService.addTask("Task 2 description", new Date(System.currentTimeMillis() + 172800000), new HashSet<>(Arrays.asList("personal", "urgent")), "life", new Date(System.currentTimeMillis() + 42100000));
            taskService.addTask("Task 3 description", new Date(System.currentTimeMillis() + 172800000), new HashSet<>(Collections.singletonList("travel")), "travel diary", new Date());

            TaskSortingStrategy sortingStrategy = new DeadlineDescendingSortingStrategy();
            List<Task> tasks = taskService.showTodoList(sortingStrategy);

            System.out.println("ToDo List with deadline descending sorting strategy:");
            tasks.forEach(task -> System.out.println("Task ID: " + task.getId() + ", Deadline: " + task.getDeadline() + ", Description: " + task.getDescription()));

            System.out.println("ToDo List with tag urgent deadline descending sorting strategy:");
            taskService.listTasks(task -> task.getTags().contains("urgent"), sortingStrategy)
                    .forEach(System.out::println);

            Task task1 = taskService.getTask("1").orElseThrow(() -> new TaskNotFoundException("Task 1 not found"));
            taskService.markTaskCompleted(task1.getId());

            System.out.println("ToDo List:");
            tasks.forEach(task -> System.out.println("Task ID: " + task.getId() + ", Description: " + task.getDescription()));

            Task task2 = taskService.getTask("2").orElseThrow(() -> new TaskNotFoundException("Task 2 not found"));
            taskService.removeTask(task2.getId());

            System.out.println("ToDo List:");
            tasks.forEach(task -> System.out.println("Task ID: " + task.getId() + ", Description: " + task.getDescription()));

            List<ActivityLog> logs = activityLogService.getActivityLogs();
            logs.forEach(log -> System.out.println("Activity ID: " + log.getActivityId() + " Entity ID: " + log.getEntityId() + " Entity type: " + log.getEntityType() + ", Action: " + log.getEntityStatus() + ", Timestamp: " + log.getTimestamp()));

            Statistics stats = statisticsService.getStatistics();
            System.out.println("Tasks Added: " + stats.getTasksAdded());
            System.out.println("Tasks Completed: " + stats.getTasksCompleted());
            System.out.println("Tasks Spilled Over: " + stats.getTasksSpilledOver());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
