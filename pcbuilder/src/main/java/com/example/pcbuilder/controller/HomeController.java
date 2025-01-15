package com.example.pcbuilder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Имя шаблона, который будет использоваться для отображения домашней страницы
    }
}
