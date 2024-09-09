package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.models.TaskRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(TaskController.class)
public class TaskControllerLightIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    void httpGetReturnGivenTask() throws Exception {
        //given
        when(taskRepository.findById(anyInt())).thenReturn(Optional.of(new Task("Task 1", LocalDateTime.now(), "Task name A")));
        when(taskRepository.existsById(anyInt())).thenReturn(true);

        //when+then
        mockMvc.perform(get("/tasks/123"))
                .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Task 1")));
    }
}
