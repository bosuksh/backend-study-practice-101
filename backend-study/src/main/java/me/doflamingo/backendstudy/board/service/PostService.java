package me.doflamingo.backendstudy.board.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import me.doflamingo.backendstudy.board.domain.Post;
import me.doflamingo.backendstudy.board.dto.PostRequestDto;
import me.doflamingo.backendstudy.board.dto.PostResponseDto;
import me.doflamingo.backendstudy.board.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public PostResponseDto writePost(PostRequestDto requestDto) {
    Post post = Post.builder()
                  .title(requestDto.getTitle())
                  .content(requestDto.getContent())
                  .writerId(requestDto.getWriterId())
                  .createdAt(LocalDateTime.now())
                  .build();
    Post savedPost = postRepository.save(post);

    return changePostToPostResponseDto(savedPost);
  }

  @Transactional(readOnly = true)
  public List<PostResponseDto> getPostList() {
    List<Post> postList = postRepository.findAll();
    List<PostResponseDto> postResponseList = new ArrayList<>();
    for (Post post : postList) {
      postResponseList.add(changePostToPostResponseDto(post));
    }
    return postResponseList;
  }

  @Transactional(readOnly = true)
  public Optional<PostResponseDto> getPostById(Long id) throws NotFoundException {
    Post findPost = postRepository.findById(id).orElseThrow(()-> new NotFoundException("Post is Not Found"));
    return Optional.of(changePostToPostResponseDto(findPost));
  }

  public Optional<PostResponseDto> updatePost(Long id, PostRequestDto requestDto) {
    return null;
  }

  public void deletePost(Long id) throws NotFoundException {
    Post post = postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post is Not Found"));
    postRepository.delete(post);

  }

  private PostResponseDto changePostToPostResponseDto(Post post) {
    return PostResponseDto.builder()
             .id(post.getId())
             .title(post.getTitle())
             .content(post.getContent())
             .writerId(post.getWriterId())
             .createdAt(post.getCreatedAt())
             .updatedAt(post.getUpdatedAt())
             .build();
  }
}
