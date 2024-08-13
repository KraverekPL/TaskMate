package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.model.Task;
import io.github.kraverekpl.TaskMate.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RepositoryRestController
public class TaskController {
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/tasks", params = {"!page", "!size", "!sort"})
    ResponseEntity<?> readAllTasks() {
        logger.warn("Read all tasks");
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/tasks")
    ResponseEntity<List<Task>> readAllTasks(Pageable page) {
        logger.warn("Custom pageable read all tasks");
        return ResponseEntity.ok(taskRepository.findAll(page).stream().toList());
    }
}
