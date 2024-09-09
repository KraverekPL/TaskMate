package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.models.TaskRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody @Valid Task toCreate) {
        logger.warn("Create task");
        Task result = taskRepository.save(toCreate);
        var uri = URI.create(String.format("/tasks/%d", result.getId()));
        return ResponseEntity.created(uri).body(result);
    }


    @GetMapping("/{id}")
    ResponseEntity<Task> readTask(@PathVariable Integer id) {
        return taskRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"!page", "!size", "!sort"})
    ResponseEntity<?> readAllTasks() {
        logger.warn("Read all tasks");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping("/search/done")
    ResponseEntity<List<Task>> readDoneTasks(@RequestParam(defaultValue = "true" ) boolean state) {
        logger.warn("Read all done tasks");
        return ResponseEntity.ok(taskRepository.findTasksByDone(state));
    }

    @GetMapping
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.warn("Custom pageable read all tasks");
        return ResponseEntity.ok(taskRepository.findAll(page).stream().toList());
    }

    @Transactional
    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@PathVariable Integer id, @RequestBody @Valid Task toUpdate) {
        logger.warn("Update task");
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.findById(id).ifPresent(task -> task.updateFrom(toUpdate));
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PatchMapping("/{id}")
    ResponseEntity<Task> toggleTask(@PathVariable Integer id) {
        logger.warn("Toggle task");
        if (taskRepository.existsById(id)) {
            Task task = taskRepository.findById(id).orElse(null);
            task.setDone(!task.isDone());
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    ResponseEntity<List<Task>> deleteAllTasks() {
        logger.warn("Delete all tasks");
        taskRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<List<Task>> deleteTask(@PathVariable("id") Integer id) {
        logger.warn("Delete task number {id}", id);
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
