package com.example.todolistapi.domain.todo.controller;

import com.example.todolistapi.domain.todo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

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
    }
}