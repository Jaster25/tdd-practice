package com.example.todolistapi.domain.todo.service;

import com.example.todolistapi.domain.todo.dto.TodoResponseDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    // 아래 코드는 클래스 상단 JUnit5 - @ExtendWith(MockitoExtension.class)으로 대체됨
    /*
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    */

    @DisplayName("할 일 목록 조회")
    @Nested
    class GetTodosTests {

        @DisplayName("할 일 목록 조회")
        @Test
        void getTodos() {
            // given
            // when
            List<TodoResponseDto> todoResponseDtos = todoService.getTodos();

            // then
            assertEquals(1L, todoResponseDtos.get(0).id());
            assertEquals(2L, todoResponseDtos.get(1).id());
        }
    }
}