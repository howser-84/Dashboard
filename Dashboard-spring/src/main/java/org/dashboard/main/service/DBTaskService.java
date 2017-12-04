package org.dashboard.main.service;

import org.dashboard.main.data.Task;
import org.dashboard.main.data.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DBTaskService implements TaskService{

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }
}
