package io.github.kraverekpl.TaskMate.model.DTO;

import io.github.kraverekpl.TaskMate.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GroupTaskWriteModel {
    private String description;
    private LocalDateTime deadline;

    public Task toTask() {
        return new Task(description, deadline);
    }

}
