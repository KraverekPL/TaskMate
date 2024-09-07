package io.github.kraverekpl.TaskMate.services;

import io.github.kraverekpl.TaskMate.models.DTO.GroupReadModel;
import io.github.kraverekpl.TaskMate.models.DTO.GroupWriteModel;
import io.github.kraverekpl.TaskMate.models.TaskGroup;
import io.github.kraverekpl.TaskMate.models.TaskGroupRepository;
import io.github.kraverekpl.TaskMate.models.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TaskGroupService {
    private TaskGroupRepository repository;
    private TaskRepository taskRepository;

    TaskGroupService(TaskGroupRepository repository, TaskRepository taskRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel source) {
        TaskGroup result = repository.save(source.toTaskGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll() {
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId) {
        if(taskRepository.existsByDoneIsFalseAndTaskGroupId(groupId))
            throw new IllegalStateException("Group has undone tasks");

        TaskGroup result = repository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Group not found"));
        result.setDone(!result.isDone());
        repository.save(result);
    }

}
