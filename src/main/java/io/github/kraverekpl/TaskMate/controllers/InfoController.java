package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.TaskConfigurationProperties;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private DataSourceProperties dataSourceProperties;
    private TaskConfigurationProperties taskConfigurationProperties;
    public InfoController(DataSourceProperties dataSourceProperties,TaskConfigurationProperties taskConfigurationProperties) {
        this.dataSourceProperties = dataSourceProperties;
        this.taskConfigurationProperties = taskConfigurationProperties;
    }
    @GetMapping(path = "/info/url")
    String url() {
        return dataSourceProperties.getUrl();
    }

    @GetMapping(path = "/info/allowMultipleTasksFromTemplate")
    boolean allowMultipleTasksFromTemplate() {
        return taskConfigurationProperties.getTemplate().isAllowMultipleTasks();
    }

}
