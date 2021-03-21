package me.doflamingo.backendstudy.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.doflamingo.backendstudy.board.dto.PostRequestDto;
import me.doflamingo.backendstudy.board.dto.PostResponseDto;
import me.doflamingo.backendstudy.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BoardController.class)
class BoardControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  BoardService boardService;

  @Test
  @DisplayName("게시글 작성")
  public void writePost() throws Exception {
    //given
    PostRequestDto mockRequest = PostRequestDto.builder()
                                      .title("Test1")
                                      .content("test")
                                      .writerId("user")
                                      .build();
    PostResponseDto mockResponse = PostResponseDto.builder()
                                        .id(1L)
                                        .title("Test1")
                                        .content("test")
                                        .writerId("user")
                                        .createdAt(LocalDateTime.now())
                                        .build();
    given(boardService.writePost(any())).willReturn(mockResponse);
    //when
    mockMvc.perform(post("/posts")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(mockRequest))
    )
    //then
    .andDo(print())
    .andExpect(status().isCreated())
    .andExpect(header().exists(HttpHeaders.LOCATION))
    .andExpect(jsonPath("id").value(1L));
  }
}