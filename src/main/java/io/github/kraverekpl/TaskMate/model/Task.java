package io.github.kraverekpl.TaskMate.model;

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
public class Task extends BaseAuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Task name cannot be empty")
    private String name;
    @NotBlank(message = "Task description cannot be empty")
    private String description;
    private boolean done;
    private LocalDateTime deadline;


    public void updateFrom(final Task source) {
        name = source.name;
        description = source.description;
        done = source.done;
        deadline = source.deadline;
    }
}
