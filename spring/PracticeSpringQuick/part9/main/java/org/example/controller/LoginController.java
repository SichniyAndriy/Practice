package org.example.controller;

import org.example.processor.LoginProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final static String MESSAGE = "message";
    private final static String MESSAGE_FAILED = "Login failed";
    private final ApplicationContext context;

    @Autowired
    public LoginController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/")
    public String loginGet() {
        return "login.html";
    }

    @PostMapping("/")
    public String loginPost(
            Model model,
            @RequestParam String username,
            @RequestParam String password
    ) {
        boolean isSuccess = context
                .getBean(LoginProcessor.class)
                .checkLogin(username, password);
        if (isSuccess) {
            return "redirect:products";
        }
        model.addAttribute(MESSAGE, MESSAGE_FAILED);
        return "login.html";
    }
}
