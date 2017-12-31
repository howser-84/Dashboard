package org.dashboard.main.controller;

import org.dashboard.main.data.Task;
import org.dashboard.main.data.TaskDTO;
import org.dashboard.main.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "hello";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody Iterable<Task> listTasks(){
        return taskService.getTasks();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody TaskDTO task){
        taskService.addTask(convert(task));
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public void remove(@RequestBody TaskDTO task){
        taskService.removeTask(convert(task));
    }

    private Task convert(TaskDTO task) {
        return modelMapper.map(task, Task.class);
    }
}
