package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.TaskConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/info")
public class InfoController {

    private DataSourceProperties dataSourceProperties;
    private TaskConfigurationProperties taskConfigurationProperties;

    public InfoController(DataSourceProperties dataSourceProperties, TaskConfigurationProperties taskConfigurationProperties) {
        this.dataSourceProperties = dataSourceProperties;
        this.taskConfigurationProperties = taskConfigurationProperties;
    }

    @GetMapping(path = "/url")
    String url() {
        return dataSourceProperties.getUrl();
    }

    @GetMapping(path = "/allowMultipleTasksFromTemplate")
    boolean allowMultipleTasksFromTemplate() {
        return taskConfigurationProperties.getTemplate().isAllowMultipleTasks();
    }

}
