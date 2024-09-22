package io.github.kraverekpl.TaskMate.repositories;

import io.github.kraverekpl.TaskMate.models.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Integer> {
    Optional<TaskGroup> findById(Integer id);
    @Query("from TaskGroup g join fetch g.tasks")
    List<TaskGroup> findAll();

    boolean existsByDoneIsFalseAndProjectId(Integer projectId);

    TaskGroup save(TaskGroup entity);
}
