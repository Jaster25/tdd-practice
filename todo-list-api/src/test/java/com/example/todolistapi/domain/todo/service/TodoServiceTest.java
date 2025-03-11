package com.example.todolistapi.domain.todo.service;

import com.example.todolistapi.domain.todo.dto.TodoResponseDto;
import com.example.todolistapi.domain.todo.entity.Todo;
import com.example.todolistapi.domain.todo.repository.TodoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

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

        @DisplayName("반환 값 확인")
        @Test
        void getTodos() {
            // given
            // when
            List<TodoResponseDto> todoResponseDtos = todoService.getTodos();

            // then
            assertEquals(1L, todoResponseDtos.get(0).id());
            assertEquals("제목1", todoResponseDtos.get(0).title());
            assertEquals("설명1", todoResponseDtos.get(0).description());
            assertEquals(true, todoResponseDtos.get(0).isCompleted());

            assertEquals(2L, todoResponseDtos.get(1).id());
            assertEquals("제목2", todoResponseDtos.get(1).title());
            assertEquals("설명2", todoResponseDtos.get(1).description());
            assertEquals(false, todoResponseDtos.get(1).isCompleted());
        }

        @DisplayName("Repository 메서드 호출 확인")
        @Test
        void verifyCall() {
            // given
            // when
            todoService.getTodos();

            // then
            BDDMockito.verify(todoRepository, Mockito.times(1))
                    .findAll();
        }

        @DisplayName("DB에 있는 값 가져오는지 확인")
        @Test
        void shouldFindAllFromRepository() {
            // given
            List<Todo> todos = List.of(
                    Todo.builder()
                            .id(1L)
                            .title("TODO1 제목")
                            .description("TODO1 설명")
                            .isCompleted(true)
                            .build(),
                    Todo.builder()
                            .id(2L)
                            .title("TODO2 제목")
                            .description("TODO2 설명")
                            .isCompleted(false)
                            .build()
            );

            given(todoRepository.findAll())
                    .willReturn(todos);

            // when
            List<TodoResponseDto> todoResponseDtos = todoService.getTodos();

            // then
            assertEquals(1L, todoResponseDtos.get(0).id());
            assertEquals("TODO1 제목", todoResponseDtos.get(0).title());
            assertEquals("TODO1 설명", todoResponseDtos.get(0).description());
            assertEquals(true, todoResponseDtos.get(0).isCompleted());

            assertEquals(2L, todoResponseDtos.get(1).id());
            assertEquals("TODO2 제목", todoResponseDtos.get(1).title());
            assertEquals("TODO2 설명", todoResponseDtos.get(1).description());
            assertEquals(false, todoResponseDtos.get(1).isCompleted());
        }
    }
}