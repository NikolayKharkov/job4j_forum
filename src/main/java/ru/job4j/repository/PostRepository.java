package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.Post;
import ru.job4j.model.User;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    List<Post> findByUser(User user);
}
