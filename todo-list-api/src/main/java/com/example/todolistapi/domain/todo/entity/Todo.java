package com.example.todolistapi.domain.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean isCompleted;

    @Builder
    public Todo(Long id, String title, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }
}
