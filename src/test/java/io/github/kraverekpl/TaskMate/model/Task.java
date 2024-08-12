package io.github.kraverekpl.TaskMate.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    private Long id;
    private String name;
    private String description;
    private boolean isDone;

}
