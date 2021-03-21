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
import java.util.ArrayList;
import java.util.List;
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
    Long fakeId = 1L;
    PostRequestDto mockRequest = createRequest(fakeId);

    Post savedPost = CreatePost(mockRequest, fakeId);

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
    Long fakeId = 1L;
    PostRequestDto mockRequest = createRequest(fakeId);

    Post savedPost = CreatePost(mockRequest, fakeId);

    given(boardRepository.findById(fakeId)).willReturn(Optional.of(savedPost));
    //when
    PostResponseDto postResponseDto = boardService.getPostById(fakeId).get();

    //then
    assertEquals(postResponseDto.getId(), fakeId);
    assertNotNull(postResponseDto.getTitle());
    assertNotNull(postResponseDto.getContent());
    assertNotNull(postResponseDto.getWriterId());

  }

  @Test
  @DisplayName("게시물 리스트 조회")
  public void getPostList() throws Exception {
    //given

    List<Post> postList = new ArrayList<>();
    for(long i = 1; i<= 10; i++ ) {
      PostRequestDto mockRequest = createRequest(i);
      postList.add(CreatePost(mockRequest, i));
    }

    given(boardRepository.findAll()).willReturn(postList);
    //when
    List<PostResponseDto> postResponseList = boardService.getPostList();

    //then
    assertEquals(postResponseList.size(), 10);

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
  private PostRequestDto createRequest(Long id) {
    return PostRequestDto.builder()
             .title("Test"+id)
             .content("test")
             .writerId("user")
             .build();
  }

}