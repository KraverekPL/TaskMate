package io.github.kraverekpl.TaskMate.reports;

import io.github.kraverekpl.TaskMate.models.event.TaskEvent;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "task_events")
public class PersistedTaskEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int taskId;

    private String task_name;
    private LocalDateTime occurrence;

    PersistedTaskEvent(TaskEvent event) {
        this.taskId = event.getTaskId();
        this.occurrence = LocalDateTime.ofInstant(event.getOcurrence(), ZoneId.systemDefault());
        this.task_name = event.getClass().getName();
    }

    public PersistedTaskEvent() {

    }
}
