package io.github.kraverekpl.TaskMate.repositories;

import io.github.kraverekpl.TaskMate.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByFullName(String name);

    Optional<User> findByEmail(String email);
    User save(User entity);

}
