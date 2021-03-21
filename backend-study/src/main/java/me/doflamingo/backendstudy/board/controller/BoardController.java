package me.doflamingo.backendstudy.board.controller;

import lombok.RequiredArgsConstructor;
import me.doflamingo.backendstudy.board.dto.PostRequestDto;
import me.doflamingo.backendstudy.board.dto.PostResponseDto;
import me.doflamingo.backendstudy.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @PostMapping("/posts")
  public ResponseEntity<?> writePost(@Valid @RequestBody PostRequestDto requestDto) {
    PostResponseDto postResponseDto = boardService.writePost(requestDto);
    URI uri = URI.create("http://localhost:8080/posts/"+postResponseDto.getId());
    return ResponseEntity.created(uri).body(postResponseDto);
  }

}
