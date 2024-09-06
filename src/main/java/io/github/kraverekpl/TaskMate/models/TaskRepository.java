package io.github.kraverekpl.TaskMate.models;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAll();

    Page<Task> findAll(Pageable page);

    Optional<Task> findById(Integer id);

    List<Task> findTasksByDone(@Param("state") boolean done);

    Task save(Task entity);

    boolean existsById(Integer id);

    void deleteAll();

    boolean existsByDoneIsFalseAndTaskGroupId(Integer groupId);


}
