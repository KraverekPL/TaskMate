package io.github.kraverekpl.TaskMate.models.DTO;

import io.github.kraverekpl.TaskMate.models.Project;
import io.github.kraverekpl.TaskMate.models.ProjectSteps;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProjectWriteModel {
    @NotBlank(message = "Project name cannot be empty")
    private String description;
    @Valid
    private List<ProjectSteps> projectSteps;

    public Project toProject() {
        Project project = new Project();
        project.setDescription(description);
        projectSteps.forEach(projectSteps1 -> projectSteps1.setProject(project));
        project.setProjectSteps(new HashSet<>(projectSteps));
        return project;
    }
}
