package io.github.kraverekpl.TaskMate.models.DTO;

import io.github.kraverekpl.TaskMate.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupTaskWriteModel {
    private String description;
    private LocalDateTime deadline;
    private String name;

    public Task toTask() {
        return new Task(description, deadline, name);
    }

}
