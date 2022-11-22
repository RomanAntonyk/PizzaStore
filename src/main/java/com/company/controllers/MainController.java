package com.company.controllers;

import com.company.service.PizzaService;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    private final UserService userService;
    private final PizzaService pizzaService;


    @Autowired
    public MainController(UserService userService, PizzaService pizzaService) {
        this.userService = userService;
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("pizzas",pizzaService.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("user",userService.getByLogin(currentPrincipalName));
        return "user/index";
    }
}
