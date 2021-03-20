package me.doflamingo.backendstudy.board.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter @Builder
public class PostRequestDto {

  @Min(value = 1, message = "제목은 최소 1자 이상입니다.")
  @Max(value = 50, message = "제목은 최소 50자를 넘을 수 없습니다.")
  private String title;

  private String content;

  @Min(value = 1, message = "아이디는 1자 이상입니다.")
  @Max(value = 30, message = "아이디 30자를 넘을 수 없습니다.")
  private String writerId;
}
