package ru.job4j.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.model.Comment;
import ru.job4j.model.Post;
import ru.job4j.model.User;
import ru.job4j.service.PostService;
import ru.job4j.service.UserService;

@Controller
public class PostControl {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("posts", postService.getPosts());
        return "index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", id == 0 ? new Post() : postService.getPostById(id));
        return "edit";
    }

    @GetMapping("/post")
    public String post(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "post";
    }

    @GetMapping("/usersPosts")
    public String usersPosts(Model model, Authentication authentication) {
        User user = getUserFromSession(authentication);
        model.addAttribute("usersPosts", postService.getUsersPosts(user));
        return "usersPosts";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        postService.deletePostById(id);
        return "redirect:/usersPosts ";
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam("id") int id,
                             @RequestParam("text") String text,
                             Authentication authentication) {
        User user = getUserFromSession(authentication);
        Post post = postService.getPostById(id);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setText(text);
        comment.setUser(user);
        post.addComment(comment);
        postService.addComment(post);
        return "redirect:/post" + "?id=" + id;
        //post?id=12
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post, Authentication authentication) {
        User user = getUserFromSession(authentication);
        post.setUser(user);
        postService.save(post);
        return "redirect:/";
    }

    private User getUserFromSession(Authentication authentication) {
        org.springframework.security.core.userdetails.User u =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return userService.findUserByName(u.getUsername());
    }
}
