package com.devops.coreproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {

    private final TodoManager todoManager;

    public TodoController() {
        this.todoManager = new TodoManager();
    }

    @GetMapping("/")
    public String getTasks(Model model) {
        model.addAttribute("tasks", todoManager.getAllTasks());
        return "index";
    }

    @PostMapping("/addTask")
    public String addTask(@RequestParam("description") String description) {
        todoManager.addTask(description);
        return "redirect:/";
    }

    @PostMapping("/markCompleted")
    public String markTaskCompleted(@RequestParam("taskIndex") int index) {
        todoManager.markTaskCompleted(index);
        return "redirect:/";
    }
}
