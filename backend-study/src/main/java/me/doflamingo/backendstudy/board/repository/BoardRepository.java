package me.doflamingo.backendstudy.board.repository;

import me.doflamingo.backendstudy.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {
}
