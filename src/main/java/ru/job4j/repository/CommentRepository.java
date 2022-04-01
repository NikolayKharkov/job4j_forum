package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Comment;
import ru.job4j.model.Post;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findAllByPostOrderByCreatedDesc(Post post);

    void deleteByPost(Post post);
}