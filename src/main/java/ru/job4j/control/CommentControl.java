package ru.job4j.control;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.Comment;
import ru.job4j.model.Post;
import ru.job4j.service.CommentService;
import ru.job4j.service.PostService;

@Controller
public class CommentControl {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @PostMapping("/addComment")
    public String addComment(@RequestParam("id") int id,
                             @RequestParam("text") String text) {
        Post post = postService.getPostById(id);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setText(text);
        comment.setUser(post.getUser());
        post.addComment(comment);
        commentService.addComment(post);
        return "redirect:/post" + "?id=" + id;
    }
}
