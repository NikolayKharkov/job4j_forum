package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.model.Post;
import ru.job4j.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id).get();
    }

    public void save(Post post) {
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
