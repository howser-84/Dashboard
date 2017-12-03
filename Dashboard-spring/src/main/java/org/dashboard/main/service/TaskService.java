package org.dashboard.main.service;

import org.dashboard.main.data.Task;

import java.util.List;

public interface TaskService {

    public void addTask(Task task);

    public List<Task> getTasks();
}
