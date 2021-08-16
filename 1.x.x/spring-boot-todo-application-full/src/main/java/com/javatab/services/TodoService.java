package com.javatab.services;

import com.javatab.models.Task;

import java.util.List;

/**
 * Created by nasir on 25/1/16.
 */
public interface TodoService {

    Task createTask(Task task);

    List<Task> getAllTasks();

    Task getTaskById(long id);

    void delete(long id);

    void evictCache();
}
