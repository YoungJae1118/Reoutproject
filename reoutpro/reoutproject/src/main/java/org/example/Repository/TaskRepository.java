package org.example.Repository;

import org.example.Entity.TaskEntity;
import org.example.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByTitle(String title);
    List<TaskEntity> findByUser(UserEntity user);
    List<TaskEntity> findByUser_id(Long userId);
}
