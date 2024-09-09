package io.github.kraverekpl.TaskMate.models.DTO;

import io.github.kraverekpl.TaskMate.models.TaskGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class GroupWriteModel {
    private String description;
    private Set<GroupTaskWriteModel> tasks;

    public TaskGroup toTaskGroup() {
        var result = new TaskGroup();
        result.setDescription(description);
        result.setTasks(
                tasks.stream()
                        .map(source -> source.toTask(result))
                        .collect(Collectors.toSet()));
        return result;
    }
}
