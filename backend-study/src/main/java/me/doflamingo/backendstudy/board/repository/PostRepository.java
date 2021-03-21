package me.doflamingo.backendstudy.board.repository;

import me.doflamingo.backendstudy.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
