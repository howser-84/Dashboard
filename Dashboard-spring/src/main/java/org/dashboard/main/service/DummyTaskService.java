package org.dashboard.main.service;

import org.dashboard.main.data.Task;

import java.util.LinkedList;
import java.util.List;

public class DummyTaskService implements TaskService {

    @Override
    public void addTask(Task task) {
        System.out.println("task added");
    }

    @Override
    public List<Task> getTasks() {
        List<Task> result = new LinkedList<>();
        Task task = new Task();
        task.setDescription("dummy task");
        task.setId(new Long(1));
        result.add(task);
        return result;
    }
}
