package io.github.kraverekpl.TaskMate.services;

import io.github.kraverekpl.TaskMate.TaskConfigurationProperties;
import io.github.kraverekpl.TaskMate.models.*;
import io.github.kraverekpl.TaskMate.models.DTO.GroupReadModel;
import io.github.kraverekpl.TaskMate.models.DTO.GroupTaskWriteModel;
import io.github.kraverekpl.TaskMate.models.DTO.GroupWriteModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService {

    private ProjectRepository repository;
    private TaskGroupRepository taskGroupRepository;

    private TaskConfigurationProperties config;

    private TaskGroupService taskGroupService;

    public ProjectService(ProjectRepository repository, TaskGroupRepository taskGroupRepository, TaskGroupService taskGroupService, TaskConfigurationProperties config) {
        this.repository = repository;
        this.taskGroupRepository = taskGroupRepository;
        this.config = config;
        this.taskGroupService = taskGroupService;
    }

    public List<Project> readAll() {
        return repository.findAll();
    }

    public Project save(Project toSave) {
        return repository.save(toSave);
    }

    public GroupReadModel createGroup(LocalDateTime deadline, int projectId) {
        if (!config.getTemplate().isAllowMultipleTasks() && taskGroupRepository.existsByDoneIsFalseAndProjectId(projectId)) {
            throw new IllegalStateException("Only one undone group per project allowed");
        }
        GroupReadModel result = repository.findById(projectId)
                .map(project -> {
                    var targetGroup = new GroupWriteModel();
                    targetGroup.setDescription(project.getDescription());
                    targetGroup.setTasks(
                            project.getProjectSteps().stream()
                                    .map(projectStep -> {
                                                var task = new GroupTaskWriteModel();
                                                task.setDescription(projectStep.getDescription());
                                                task.setDeadline(deadline.plusDays(projectStep.getDaysToDeadline()));
                                                return task;
                                            }
                                    )
                                    .collect(Collectors.toSet())
                    );
                    return taskGroupService.createGroup(targetGroup);
                }).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        return result;
    }
}
