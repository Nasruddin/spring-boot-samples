package com.javatab.services;

import com.javatab.models.Task;
import com.javatab.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nasir on 25/1/16.
 */
@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class TodoServiceImpl implements TodoService {

    private TaskRepository repository;

    @Autowired
    public TodoServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(
            propagation = Propagation.SUPPORTS,
            readOnly = false)
    @CachePut(
            value = "todos",
            key = "#result.id")
    public Task createTask(Task task) {
        return repository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    @Cacheable(
            value = "todos",
            key = "#id")
    public Task getTaskById(long id) {
        return repository.findOne(id);
    }

    @Override
    @Transactional(
            propagation = Propagation.SUPPORTS,
            readOnly = false)
    @CacheEvict(
            value = "todos",
            key = "#id")
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    @CacheEvict(
            value = "todos",
            allEntries = true)
    public void evictCache() {

    }
}
