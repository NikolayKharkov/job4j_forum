package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.model.*;
import ru.job4j.repository.*;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository users;

    @Autowired
    private AuthorityRepository authorities;

    public String inCodePassword(String password) {
        return encoder.encode(password);
    }

    public Authority findAuthority(String authority) {
        return authorities.findByAuthority(authority);
    }

    public User saveUser(User user) {
        return users.save(user);
    }

    public User findUserByName(String name) {
        return users.findByUsername(name);
    }
}