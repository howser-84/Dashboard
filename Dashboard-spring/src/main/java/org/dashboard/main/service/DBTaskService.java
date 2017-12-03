package org.dashboard.main.service;

import org.dashboard.main.data.Task;
import org.dashboard.main.data.TaskDAO;

public class DBTaskService implements TaskService{

    private TaskDAO taskDAO = new TaskDAO();


    @Override
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    @Override
    public Iterable<Task> getTasks() {
        return taskDAO.getAllTasks();
    }
}
