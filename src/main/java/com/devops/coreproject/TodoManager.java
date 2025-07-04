package com.devops.coreproject;

import java.util.ArrayList;
import java.util.List;

public class TodoManager {

    static class Task {
        String description;
        boolean isCompleted;

        Task(String description) {
            this.description = description;
            this.isCompleted = false;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void markCompleted() {
            isCompleted = true;
        }
    }

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    public List<Task> getPendingTasks() {
        List<Task> pending = new ArrayList<>();
        for (Task t : tasks) {
            if (!t.isCompleted()) {
                pending.add(t);
            }
        }
        return pending;
    }

    public List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();
        for (Task t : tasks) {
            if (t.isCompleted()) {
                completed.add(t);
            }
        }
        return completed;
    }

    public boolean markTaskCompleted(int index) {
        if (index >= 0 && index < tasks.size() && !tasks.get(index).isCompleted()) {
            tasks.get(index).markCompleted();
            return true;
        }
        return false;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}
