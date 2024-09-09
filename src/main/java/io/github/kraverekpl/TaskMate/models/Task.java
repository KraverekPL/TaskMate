package io.github.kraverekpl.TaskMate.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Task name cannot be empty")
    private String name;
    @NotBlank(message = "Task description cannot be empty")
    private String description;
    private boolean done;
    private LocalDateTime deadline;
    @Embedded
    private Audit audit = new Audit();
    @ManyToOne
    @JoinColumn(name = "task_group_id")
    private TaskGroup taskGroup;

    public Task(String description, LocalDateTime deadline, String name) {
        this(description, deadline, name, null);
    }

    public Task(String description, LocalDateTime deadline, String name, TaskGroup taskGroup) {
        this.description = description;
        this.deadline = deadline;
        this.name = name;
        if (taskGroup != null) {
            this.taskGroup = taskGroup;
        }
    }


    public void updateFrom(final Task source) {
        name = source.name;
        description = source.description;
        done = source.done;
        deadline = source.deadline;
        taskGroup = source.taskGroup;
    }
}
