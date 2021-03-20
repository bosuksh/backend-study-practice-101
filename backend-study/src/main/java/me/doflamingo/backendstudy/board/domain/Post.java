package me.doflamingo.backendstudy.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity @AllArgsConstructor
@Getter @Builder @NoArgsConstructor
public class Post {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Min(value = 1, message = "제목은 최소 1자 이상입니다.")
  @Max(value = 50, message = "제목은 최소 50자를 넘을 수 없습니다.")
  private String title;

  private String content;

  @Min(value = 1, message = "아이디는 1자 이상입니다.")
  @Max(value = 30, message = "아이디 30자를 넘을 수 없습니다.")
  private String writerId;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;
}
