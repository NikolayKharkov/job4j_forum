package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;


    public List<Post> getUsersPosts(User user) {
        return postRepository.findByUser(user);
    }

    public List<Post> getPosts() {
        return (List<Post>) postRepository.findAll();
    }

    @Transactional
    public void deletePostById(int id) {
        Post post = getPostById(id);
        commentService.deleteByPost(post);
        postRepository.deleteById(id);
    }

    @Transactional
    public Post getPostById(int id) {
        Post post = postRepository.findById(id).get();
        post.setComments(commentService.findAllByPost(post));
        return post;
    }

    public void addComment(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public void saveOrUpdate(Post post) {
        int id = post.getId();
        if (id != 0) {
            Post postUpdating = getPostById(id);
            postUpdating.setName(post.getName());
            postUpdating.setDescription(post.getDescription());
            postRepository.save(postUpdating);
        } else {
            postRepository.save(post);
        }
    }
}
