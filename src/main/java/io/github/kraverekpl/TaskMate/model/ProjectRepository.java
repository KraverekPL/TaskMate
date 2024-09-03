package io.github.kraverekpl.TaskMate.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findById(Integer id);
    @Query("from Project p join fetch p.projectSteps")
    List<Project> findAll();

    TaskGroup save(TaskGroup entity);

}
