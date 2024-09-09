package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.models.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @Bean
    DataSource e2eTestDataSource() {
        var result = new DriverManagerDataSource("jdbc:h2:file:./testE2Edb", "sa", "");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }

    @Test
    void httpGetAllTasks() {
        //given
        taskRepository.save(new Task("Task 1", LocalDateTime.now(), "Task name A"));
        taskRepository.save((new Task("Task 2", LocalDateTime.now().plusDays(1), "Task name B")));

        //when
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);
        assertThat(result).hasSize(2);
    }
}
