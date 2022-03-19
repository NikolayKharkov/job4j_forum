package ru.job4j.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

import java.util.Objects;


@Controller
public class RegControl {

    @Autowired
    private UserService userService;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (Objects.isNull(userService.findUserByName(user.getUsername()))) {
            user.setEnabled(true);
            user.setPassword(userService.inCodePassword(user.getPassword()));
            user.setAuthority(userService.findAuthority("ROLE_USER"));
            userService.saveUser(user);
            return "redirect:/login";
        } else {
            model.addAttribute("errorMessage", "Указанное имя уже занято !");
            return "reg";
        }

    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
