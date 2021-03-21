package me.doflamingo.backendstudy.board.service;

import javassist.NotFoundException;
import me.doflamingo.backendstudy.board.domain.Post;
import me.doflamingo.backendstudy.board.dto.PostRequestDto;
import me.doflamingo.backendstudy.board.dto.PostResponseDto;
import me.doflamingo.backendstudy.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class BoardServiceTest {

  @InjectMocks
  BoardService boardService;

  @Mock
  BoardRepository boardRepository;


  @Test
  @DisplayName("게시물 작성")
  public void writePost() {
    //given
    PostRequestDto mockRequest = createRequest();

    Post savedPost = CreatePost(mockRequest, 1L);

    given(boardRepository.save(any())).willReturn(savedPost);
    //when
    PostResponseDto postResponseDto = boardService.writePost(mockRequest);

    //then
    assertEquals(postResponseDto.getId(), savedPost.getId());
    assertEquals(postResponseDto.getTitle(), savedPost.getTitle());
    assertEquals(postResponseDto.getContent(), savedPost.getContent());
    assertEquals(postResponseDto.getWriterId(), savedPost.getWriterId());
    assertNull(postResponseDto.getUpdatedAt());

  }



  @Test
  @DisplayName("게시물 조회 by Id")
  public void getPostById() throws Exception {
    //given
    PostRequestDto mockRequest = createRequest();

    Long fakeId = 1L;
    Post savedPost = CreatePost(mockRequest, fakeId);

    given(boardRepository.findById(fakeId)).willReturn(Optional.of(savedPost));
    //when
    PostResponseDto postResponseDto = boardService.getPostById(fakeId).orElseThrow(() -> new NotFoundException("post is not found"));

    //then
    assertEquals(postResponseDto.getId(), fakeId);
    assertNotNull(postResponseDto.getTitle());
    assertNotNull(postResponseDto.getContent());
    assertNotNull(postResponseDto.getWriterId());

  }
  private Post CreatePost(PostRequestDto mockRequest, Long id) {
    return Post.builder()
             .id(id)
             .title(mockRequest.getTitle())
             .content(mockRequest.getContent())
             .writerId(mockRequest.getWriterId())
             .createdAt(LocalDateTime.now())
             .build();
  }
  private PostRequestDto createRequest() {
    return PostRequestDto.builder()
             .title("Test1")
             .content("test")
             .writerId("user")
             .build();
  }

}