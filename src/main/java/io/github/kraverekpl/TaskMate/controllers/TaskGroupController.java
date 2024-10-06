package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.controllers.advice.GlobalExceptionHandlerProcessing;
import io.github.kraverekpl.TaskMate.models.DTO.GroupReadModel;
import io.github.kraverekpl.TaskMate.models.DTO.GroupWriteModel;
import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.repositories.TaskGroupRepository;
import io.github.kraverekpl.TaskMate.services.TaskGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/groups")
@GlobalExceptionHandlerProcessing
public class TaskGroupController {
    private TaskGroupService taskGroupService;
    private TaskGroupRepository taskGroupRepository;

    public TaskGroupController(TaskGroupService taskGroupService, TaskGroupRepository taskGroupRepository) {
        this.taskGroupService = taskGroupService;
        this.taskGroupRepository = taskGroupRepository;
    }

    @PostMapping
    public ResponseEntity<GroupReadModel> createGroup(@RequestBody GroupWriteModel request) {
        var result = taskGroupService.createGroup(request);
        var uri = URI.create("/groups/" + result.getId());
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping
    public ResponseEntity<List<GroupReadModel>> readAllGroups() {
        return ResponseEntity.ok(taskGroupService.readAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GroupReadModel> toggleGroup(@PathVariable int id) {
        if (!taskGroupRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskGroupService.toggleGroup(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> readGroupTasks(@PathVariable int id) {
        if (!taskGroupRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(taskGroupRepository
                    .findById(id)
                    .map(taskGroup -> taskGroup
                            .getTasks()
                            .stream()
                            .toList())
                    .orElse(Collections.emptyList()));
        }
    }
}
