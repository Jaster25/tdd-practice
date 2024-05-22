package com.example.todolistapi.domain.todo.service;

import com.example.todolistapi.domain.todo.dto.TodoResponseDto;
import com.example.todolistapi.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoResponseDto> getTodos() {

        todoRepository.findAll();

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
