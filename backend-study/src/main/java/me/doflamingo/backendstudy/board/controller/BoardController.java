package me.doflamingo.backendstudy.board.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import me.doflamingo.backendstudy.board.dto.PostRequestDto;
import me.doflamingo.backendstudy.board.dto.PostResponseDto;
import me.doflamingo.backendstudy.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class BoardController {

  private final BoardService boardService;

  @PostMapping
  public ResponseEntity<?> writePost(@Valid @RequestBody PostRequestDto requestDto) {
    PostResponseDto postResponseDto = boardService.writePost(requestDto);
    URI uri = URI.create("http://localhost:8080/posts/"+postResponseDto.getId());
    return ResponseEntity.created(uri).body(postResponseDto);
  }

  @GetMapping
  public ResponseEntity<?> getPostList() {
    return ResponseEntity.ok(boardService.getPostList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPost(@PathVariable Long id) throws NotFoundException {
    PostResponseDto postResponseDto = boardService.getPostById(id)
                                        .orElseThrow(() -> new NotFoundException("post is not found"));
    return ResponseEntity.ok(postResponseDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws NotFoundException {
    PostResponseDto postResponseDto = boardService.updatePost(id, requestDto)
                                        .orElseThrow(() -> new NotFoundException("post is not found"));
    return ResponseEntity.ok(postResponseDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePost(@PathVariable Long id) {
    boardService.deletePost(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
