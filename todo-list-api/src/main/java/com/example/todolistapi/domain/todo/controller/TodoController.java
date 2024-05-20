package com.example.todolistapi.domain.todo.controller;

import com.example.todolistapi.domain.todo.dto.TodoResponseDto;
import com.example.todolistapi.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/api/todos")
    public List<TodoResponseDto> getTodosApi() {
        return todoService.getTodos();
    }
}
