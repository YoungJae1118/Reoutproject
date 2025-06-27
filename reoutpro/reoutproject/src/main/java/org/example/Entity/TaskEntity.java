package org.example.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    public TaskEntity(Long id, UserEntity userEntity, String title, String content) {
        this.id = id;
        this.user = userEntity;
        this.title = title;
        this.content = content;
    }

    public TaskEntity() {

    }
}
