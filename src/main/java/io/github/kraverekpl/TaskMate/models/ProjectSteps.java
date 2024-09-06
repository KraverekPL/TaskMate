package io.github.kraverekpl.TaskMate.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_steps")
@Getter
@Setter
@NoArgsConstructor
public class ProjectSteps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Project steps name cannot be empty")
    private String description;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    private int daysToDeadline;
}
