package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private static final String NAME = "Andriy";
    private static final String COLOR = "red";

    @RequestMapping("/home")
    public String home(
            Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color
    ) {
        model.addAttribute("username", name == null || name.isBlank() ? NAME : name);
        model.addAttribute("color", color == null || color.isEmpty() ? COLOR : color);
        return "home.html";
    }

    @RequestMapping("/home/{color}")
    public String home(
            Model model,
            @PathVariable String color
    ) {
        model.addAttribute("username", NAME);
        model.addAttribute("color", color);
        return "home.html";
    }
}
