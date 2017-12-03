package com.mypieceofcode.evfinder.repository;

import com.mypieceofcode.evfinder.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
