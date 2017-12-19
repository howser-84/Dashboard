package org.dashboard.main.service;

import org.dashboard.main.data.Task;
import org.dashboard.main.data.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class DBTaskService implements TaskService{

    @Autowired
    private TaskDAO taskRepository;


    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void removeTask(Task task) {
        taskRepository.delete(task);
    }
}
