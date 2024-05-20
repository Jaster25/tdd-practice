package com.example.todolistapi.domain.todo.controller;

import com.example.todolistapi.domain.todo.dto.TodoResponseDto;
import com.example.todolistapi.domain.todo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TodoService todoService;

    @DisplayName("할 일 목록 조회하기 API")
    @Nested
    class GetTodosApiTests {

        @DisplayName("API 호출")
        @Test
        void callApi () throws Exception {
            // given
            // when
            // then
            mockMvc.perform(get("/api/todos")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @DisplayName("응답 값 타입 확인")
        @Test
        void checkResponseDtoType() throws Exception {
            // given
            // when
            // then
            mockMvc.perform(get("/api/todos")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].title").value("독서"))
                    .andExpect(jsonPath("$[0].description").value("주식 책 읽어야 함"))
                    .andExpect(jsonPath("$[0].isCompleted").value(false));
        }
        
        @DisplayName("할 일 목록을 서비스 로직에서 조회하는지 확인")
        @Test
        void shouldCallGetTodosInService() throws Exception {
            // given
            TodoResponseDto todoResponseDto1 = TodoResponseDto.builder()
                    .id(1L)
                    .title("제목1")
                    .description("설명1")
                    .isCompleted(false)
                    .build();
            TodoResponseDto todoResponseDto2 = TodoResponseDto.builder()
                    .id(2L)
                    .title("제목2")
                    .description("설명2")
                    .isCompleted(true)
                    .build();

            given(todoService.getTodos())
                    .willReturn(List.of(todoResponseDto1, todoResponseDto2));

            // when
            // then
            mockMvc.perform(get("/api/todos")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].title").value("제목1"))
                    .andExpect(jsonPath("$[1].id").value(2))
                    .andExpect(jsonPath("$[1].title").value("제목2"));
        }
    }
}