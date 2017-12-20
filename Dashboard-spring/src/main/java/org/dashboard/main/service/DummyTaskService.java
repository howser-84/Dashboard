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
    public Iterable<Task> getTasks() {
        List<Task> result = new LinkedList<>();
        Task task = new Task();
        task.setName("dummy");
        task.setDescription("this is a dummy task");
        task.setId(new Long(123));
        result.add(task);

        Task anotherTask = new Task();
        anotherTask.setName("another dummy");
        anotherTask.setDescription("this is yet another dummy task");
        anotherTask.setId(new Long(127));
        result.add(anotherTask);
        return result;
    }

    @Override
    public void removeTask(Task task) {
        //empty
    }
}
