package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void httpGetReturnGivenTask() throws Exception {
        //given
        int taskId = taskRepository.save(new Task("Task 1", LocalDateTime.now())).getId();

        //when+then
        mockMvc.perform(get("/tasks/" + taskId))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

}
