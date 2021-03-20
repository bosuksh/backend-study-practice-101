package me.doflamingo.backendstudy.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.doflamingo.backendstudy.board.dto.PostRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class BoardControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @DisplayName("게시글 작성")
  public void writePost() throws Exception {
    //given
    PostRequestDto postRequestDto = PostRequestDto.builder()
                                      .title("Test1")
                                      .content("test")
                                      .writerId("user")
                                      .build();

    //when
    mockMvc.perform(post("/posts")
      .accept(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(postRequestDto))
    )
    //then
    .andDo(print())
    .andExpect(status().isOk());
  }
}