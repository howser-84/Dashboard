package org.dashboard.main.controller;

import org.dashboard.main.data.Task;
import org.dashboard.main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class TaskController {

    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "/")
    public String home(){
        return "hello";
    }

    @RequestMapping(value = "/list")
    public @ResponseBody Iterable<Task> listTasks(){
        return taskService.getTasks();
    }
}
