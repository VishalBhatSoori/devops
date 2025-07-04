package com.devops.coreproject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoManagerTest {

    private TodoManager manager;

    @BeforeEach
    public void setup() {
        manager = new TodoManager();
    }

    @Test
    public void testAddTask() {
        manager.addTask("Buy milk");
        List<TodoManager.Task> allTasks = manager.getAllTasks();
        assertEquals(1, allTasks.size());
        assertEquals("Buy milk", allTasks.get(0).getDescription());
        assertFalse(allTasks.get(0).isCompleted());
    }

    @Test
    public void testMarkTaskCompleted() {
        manager.addTask("Do laundry");
        assertTrue(manager.markTaskCompleted(0));
        assertTrue(manager.getAllTasks().get(0).isCompleted());
    }

    @Test
    public void testGetPendingAndCompletedTasks() {
        manager.addTask("Task 1");
        manager.addTask("Task 2");
        manager.markTaskCompleted(0);

        List<TodoManager.Task> pending = manager.getPendingTasks();
        List<TodoManager.Task> completed = manager.getCompletedTasks();

        assertEquals(1, pending.size());
        assertEquals("Task 2", pending.get(0).getDescription());

        assertEquals(1, completed.size());
        assertEquals("Task 1", completed.get(0).getDescription());
    }

    @Test
    public void testInvalidComplete() {
        manager.addTask("Something");
        assertFalse(manager.markTaskCompleted(1)); // invalid index
    }
}
