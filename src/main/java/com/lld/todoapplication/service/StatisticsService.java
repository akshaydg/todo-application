package com.lld.todoapplication.service;

import com.lld.todoapplication.model.Statistics;
import com.lld.todoapplication.model.Task;
import com.lld.todoapplication.model.TaskStatus;
import com.lld.todoapplication.repository.ITaskRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class StatisticsService {
    private final ITaskRepository taskRepository;

    public StatisticsService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Statistics getStatistics() {
        return getStatistics(Optional.empty(), Optional.empty());
    }

    public Statistics getStatistics(Optional<Date> from, Optional<Date> to) {
        List<Task> tasks = taskRepository.listTasks(task -> true);

        Date fromDate = from.orElse(new Date(Long.MIN_VALUE));
        Date toDate = to.orElse(new Date(Long.MAX_VALUE));

        int tasksAdded = (int) tasks.stream()
                .filter(task -> !task.getCreatedAt().before(fromDate) && !task.getCreatedAt().after(toDate))
                .count();

        int tasksCompleted = (int) tasks.stream()
                .filter(task -> List.of(TaskStatus.COMPLETED, TaskStatus.DELAYED).contains(task.getStatus()))
                .filter(task -> task.getCompletedAt() != null)
                .filter(task -> !task.getCompletedAt().before(fromDate) && !task.getCompletedAt().after(toDate))
                .count();

        int tasksSpilledOver = (int) tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.IN_PROGRESS)
                .filter(task -> task.getDeadline().before(new Date()))
                .count();

        Statistics statistics = new Statistics();
        statistics.setTasksAdded(tasksAdded);
        statistics.setTasksCompleted(tasksCompleted);
        statistics.setTasksSpilledOver(tasksSpilledOver);

        return statistics;
    }
}

