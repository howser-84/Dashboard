package org.dashboard.main.controller;

import org.dashboard.main.data.Task;
import org.dashboard.main.data.TaskDTO;
import org.dashboard.main.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/")
    public String home(){
        return "hello";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/list")
    public @ResponseBody Iterable<Task> listTasks(){
        return taskService.getTasks();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/add")
    public void add(@RequestBody TaskDTO task){
        taskService.addTask(convert(task));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/remove")
    public void remove(@RequestBody TaskDTO task){
        taskService.removeTask(convert(task));
    }

    private Task convert(TaskDTO task) {
        return modelMapper.map(task, Task.class);
    }
}
