package me.doflamingo.backendstudy.board.service;

import lombok.RequiredArgsConstructor;
import me.doflamingo.backendstudy.board.domain.Post;
import me.doflamingo.backendstudy.board.dto.PostRequestDto;
import me.doflamingo.backendstudy.board.dto.PostResponseDto;
import me.doflamingo.backendstudy.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public PostResponseDto writePost(PostRequestDto requestDto) {
    Post post = Post.builder()
                  .title(requestDto.getTitle())
                  .content(requestDto.getContent())
                  .writerId(requestDto.getWriterId())
                  .createdAt(LocalDateTime.now())
                  .build();
    Post savedPost = boardRepository.save(post);

    return PostResponseDto.builder()
             .id(savedPost.getId())
             .title(savedPost.getTitle())
             .content(savedPost.getContent())
             .writerId(savedPost.getWriterId())
             .createdAt(savedPost.getCreatedAt())
             .build();
  }

  public List<PostResponseDto> getPostList() {
    return null;
  }

  public Optional<PostResponseDto> getPostById(Long id) {
    return null;
  }

  public Optional<PostResponseDto> updatePost(Long id, PostRequestDto requestDto) {
    return null;
  }

  public void deletePost(Long id) {

  }
}
