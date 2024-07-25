package com.lld.todoapplication.tasksortingstrategies;

import com.lld.todoapplication.model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeadlineDescendingSortingStrategy implements TaskSortingStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        List<Task> modifiableList = new ArrayList<>(tasks);
        modifiableList.sort(Comparator.comparing(Task::getDeadline).reversed());
        return modifiableList;
    }
}
