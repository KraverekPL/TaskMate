package io.github.kraverekpl.TaskMate.reports;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final TaskRepository taskRepository;
    private final PersistedTaskEventRepository eventRepository;

    public ReportController(TaskRepository taskRepository, PersistedTaskEventRepository repository) {
        this.taskRepository = taskRepository;
        this.eventRepository = repository;
    }

    @GetMapping("/count")
    ResponseEntity<TaskWithChangesCount> readTaskWithCount(@PathVariable int taskId) {
        return taskRepository.findById(taskId)
                .map(task -> new TaskWithChangesCount(task, eventRepository.findAllByTaskId(taskId)))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private static class TaskWithChangesCount {
        public String description;
        public boolean done;
        public int changesCount;

        TaskWithChangesCount(final Task task, final List<PersistedTaskEvent> events) {
            this.description = task.getDescription();
            this.done = task.isDone();
            this.changesCount = events.size();
        }
    }
}

