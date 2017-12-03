package org.dashboard.main.service;

import org.dashboard.main.data.Task;

public interface TaskService {

    public void addTask(Task task);

    public Iterable<Task> getTasks();
}
