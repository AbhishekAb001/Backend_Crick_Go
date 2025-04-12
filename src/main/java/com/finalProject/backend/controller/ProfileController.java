package com.finalProject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalProject.backend.model.authModels.Users;
import com.finalProject.backend.service.ProfileService;

@RestController
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/profile")
    public Users getUser(@RequestParam String username){
        return profileService.getUser(username); 
    }

    
}
