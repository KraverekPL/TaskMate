package io.github.kraverekpl.TaskMate.services;

import io.github.kraverekpl.TaskMate.TaskConfigurationProperties;
import io.github.kraverekpl.TaskMate.models.ProjectRepository;
import io.github.kraverekpl.TaskMate.models.TaskGroupRepository;
import io.github.kraverekpl.TaskMate.models.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ProjectService projectService(
            final ProjectRepository repository,
            final TaskGroupRepository taskGroupRepository,
            final TaskGroupService taskGroupService,
            final TaskConfigurationProperties config) {
        return new ProjectService(repository, taskGroupRepository, taskGroupService, config);
    }

    @Bean
    public TaskGroupService taskGroupService(
            final TaskGroupRepository repository,
            final TaskRepository taskRepository) {
        return new TaskGroupService(repository, taskRepository);
    }
}
