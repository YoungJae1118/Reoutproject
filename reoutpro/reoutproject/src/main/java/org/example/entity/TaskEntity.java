package org.example.entity;

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

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean softDeleted;

    public TaskEntity(UserEntity userEntity, String title, String content) {
        this.user = userEntity;
        this.title = title;
        this.content = content;
    }


    public TaskEntity() {

    }

    public void updateTitleAndContent(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;
    }

    public void taskSoftDeleted() {
        this.softDeleted = true;
    }
}
