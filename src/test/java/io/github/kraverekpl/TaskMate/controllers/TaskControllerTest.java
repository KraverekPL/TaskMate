package io.github.kraverekpl.TaskMate.controllers;

import io.github.kraverekpl.TaskMate.models.Task;
import io.github.kraverekpl.TaskMate.models.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask() {
        Task task = new Task();
        task.setName("Test Task");

        ResponseEntity<Task> response = taskController.createTask(task);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void readTask() {
        Integer id = 1;
        Task task = new Task();
        task.setId(id);

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        ResponseEntity<Task> response = taskController.readTask(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(taskRepository, times(1)).findById(id);
    }

    @Test
    void readAllTasks() {
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskRepository.findAll()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = (ResponseEntity<List<Task>>) taskController.readAllTasks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void readAllTasksPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        Page<Task> page = new PageImpl<>(tasks);
        when(taskRepository.findAll(pageable)).thenReturn(page);

        ResponseEntity<List<Task>> response = taskController.readAllTasks(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
        verify(taskRepository, times(1)).findAll(pageable);
    }

    @Test
    void updateTask() {
        Integer id = 1;
        Task task = new Task();
        task.setId(id);

        when(taskRepository.existsById(id)).thenReturn(true);
        when(taskRepository.save(task)).thenReturn(task);

        ResponseEntity<Task> response = (ResponseEntity<Task>) taskController.updateTask(id, task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
        verify(taskRepository, times(1)).existsById(id);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void updateTaskNotFound() {
        Integer id = 1;
        Task task = new Task();
        task.setId(id);

        when(taskRepository.existsById(id)).thenReturn(false);

        ResponseEntity<Task> response = (ResponseEntity<Task>) taskController.updateTask(id, task);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(taskRepository, times(1)).existsById(id);
    }

    @Test
    void deleteAllTasks() {
        taskController.deleteAllTasks();
        verify(taskRepository, times(1)).deleteAll();
    }

    @Test
    void deleteTask() {
        Integer id = 1;
        taskController.deleteTask(id);
        verify(taskRepository, times(1)).deleteById(id);
    }
}