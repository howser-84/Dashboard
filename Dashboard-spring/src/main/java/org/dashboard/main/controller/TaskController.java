package org.dashboard.main.controller;

import org.dashboard.main.data.Task;
import org.dashboard.main.data.TaskDTO;
import org.dashboard.main.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/list")
    public @ResponseBody Iterable<Task> listTasks(){
        return taskService.getTasks();
    }

    @RequestMapping(value = "/add")
    public @ResponseBody String add(@RequestBody TaskDTO task){
        taskService.addTask(convert(task));
        return "Ok";
    }

    private Task convert(TaskDTO task) {
        return modelMapper.map(task, Task.class);
    }
}
