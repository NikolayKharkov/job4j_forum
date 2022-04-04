package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.Comment;
import ru.job4j.model.Post;
import ru.job4j.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    public void deleteByPost(Post post) {
        commentRepository.deleteByPost(post);
    }

    public List<Comment> findAllByPost(Post post) {
        return commentRepository.findAllByPostOrderByCreatedDesc(post);
    }

    public void addComment(Post post) {
        postService.addComment(post);
    }
}
