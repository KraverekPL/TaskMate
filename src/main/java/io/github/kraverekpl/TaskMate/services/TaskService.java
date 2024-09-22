package io.github.kraverekpl.TaskMate.services;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.repositories.TaskRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Async
    public CompletableFuture<List<Task>> findAllTasksAsync() {
        return CompletableFuture.supplyAsync(taskRepository::findAll);
    }
}
