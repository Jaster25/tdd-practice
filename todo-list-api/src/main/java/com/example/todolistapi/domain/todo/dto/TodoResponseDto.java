package com.example.todolistapi.domain.todo.dto;

import lombok.Builder;

public record TodoResponseDto(
        Long id,
        String title,
        String description,
        Boolean isCompleted
) {
    @Builder
    public TodoResponseDto {
    }
}
