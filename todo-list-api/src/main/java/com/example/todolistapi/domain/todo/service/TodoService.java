package com.example.todolistapi.domain.todo.service;

import com.example.todolistapi.domain.todo.dto.TodoResponseDto;

import java.util.List;

public class TodoService {

    public List<TodoResponseDto> getTodos() {
        return List.of(
                TodoResponseDto.builder()
                        .id(1L)
                        .title("제목1")
                        .description("설명1")
                        .isCompleted(true)
                        .build(),
                TodoResponseDto.builder()
                        .id(2L)
                        .title("제목2")
                        .description("설명2")
                        .isCompleted(false)
                        .build()
        );
    }
}
