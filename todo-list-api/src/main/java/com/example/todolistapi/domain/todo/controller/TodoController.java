package com.example.todolistapi.domain.todo.controller;

import com.example.todolistapi.domain.todo.dto.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    @GetMapping("/api/todos")
    public List<TodoResponseDto> getTodosApi() {
        return List.of(TodoResponseDto.builder()
                .id(1L)
                .title("독서")
                .description("주식 책 읽어야 함")
                .isCompleted(false)
                .build()
        );
    }
}
