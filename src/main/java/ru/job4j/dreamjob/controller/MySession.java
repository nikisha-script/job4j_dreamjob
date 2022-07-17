package ru.job4j.dreamjob.controller;

import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.User;

import javax.servlet.http.HttpSession;

public class MySession {

    public static void getSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setEmail("Guest");
        }
        model.addAttribute("user", user);
    }

}
