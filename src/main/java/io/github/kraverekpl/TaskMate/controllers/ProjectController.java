package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.models.DTO.ProjectWriteModel;
import io.github.kraverekpl.TaskMate.models.Project;
import io.github.kraverekpl.TaskMate.models.ProjectSteps;
import io.github.kraverekpl.TaskMate.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    String showProjects(Model model) {
        model.addAttribute("project", new ProjectWriteModel());
        return "/projects";
    }

    @PostMapping
    String addProject(
            @ModelAttribute("project") @Valid ProjectWriteModel current,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "/projects";
        }
        projectService.save(current);
        model.addAttribute("project", new ProjectWriteModel());
        model.addAttribute("projects", getProjects());
        model.addAttribute("message", "Projekt został dodany");
        return "/projects";
    }

    @PostMapping(params = "addStep")
    String addStep(@ModelAttribute("project") ProjectWriteModel project) {
        project.getProjectSteps().add(new ProjectSteps());
        return "/projects";
    }

    @PostMapping("/{id}")
    String createGroup(@ModelAttribute("project") ProjectWriteModel project,
                       Model model,
                       @PathVariable int id,
                       @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime deadline) {
        try {
            projectService.createGroup(deadline, id);
            model.addAttribute("message", "Grupa została utworzona");
        } catch (IllegalArgumentException | IllegalStateException e) {
            model.addAttribute("message", "Nie można utworzyć grupy.");
        }
        return "/projects";
    }

    @ModelAttribute("projects")
    List<Project> getProjects() {
        return projectService.readAll();
    }
}
