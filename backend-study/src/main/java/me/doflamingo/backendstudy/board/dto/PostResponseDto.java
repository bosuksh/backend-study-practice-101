package me.doflamingo.backendstudy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class PostResponseDto {

  private Long id;

  private String title;

  private String content;

  private String writerId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
