package com.finalProject.backend.controller;

import com.finalProject.backend.model.authModels.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String loginPage() {
        return "Hello page";
    }
}
