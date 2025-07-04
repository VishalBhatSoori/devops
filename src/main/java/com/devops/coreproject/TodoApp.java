package com.devops.coreproject;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {

    static class Task {
        String description;
        boolean isCompleted;

        Task(String description) {
            this.description = description;
            this.isCompleted = false;
        }
    }

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== TODO APP =====");
            System.out.println("1. Add Task");
            System.out.println("2. Show Completed Tasks");
            System.out.println("3. Show Pending Tasks");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    showCompletedTasks();
                    break;
                case "3":
                    showPendingTasks();
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added successfully.");
    }

    private static void showCompletedTasks() {
        System.out.println("\n-- Completed Tasks --");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isCompleted) {
                System.out.println((i + 1) + ". " + tasks.get(i).description);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No completed tasks.");
        }
        promptReturnToMain();
    }

    private static void showPendingTasks() {
        while (true) {
            System.out.println("\n-- Pending Tasks --");
            ArrayList<Integer> pendingIndexes = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                if (!tasks.get(i).isCompleted) {
                    System.out.println((pendingIndexes.size() + 1) + ". " + tasks.get(i).description);
                    pendingIndexes.add(i);
                }
            }

            if (pendingIndexes.isEmpty()) {
                System.out.println("No pending tasks.");
                break;
            }

            System.out.println("Select task number to mark as completed, or type 0 to return to main menu:");
            String input = scanner.nextLine();
            try {
                int selected = Integer.parseInt(input);
                if (selected == 0) {
                    break;
                } else if (selected > 0 && selected <= pendingIndexes.size()) {
                    tasks.get(pendingIndexes.get(selected - 1)).isCompleted = true;
                    System.out.println("Task marked as completed.");
                } else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    private static void promptReturnToMain() {
        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }
}
