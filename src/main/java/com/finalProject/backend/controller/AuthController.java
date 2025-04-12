package com.finalProject.backend.controller;

import com.finalProject.backend.model.authModels.LoginModel;
import com.finalProject.backend.model.authModels.SignupModel;
import com.finalProject.backend.model.authModels.Users;
import com.finalProject.backend.service.Auth.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(authService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginCred) {
        return  authService.verifyLogin(loginCred);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupModel registerCred) {
        System.out.println(registerCred);
        if (registerCred != null && registerCred.getUsername() != null && registerCred.getPassword() != null) {
            authService.register(registerCred);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Invalid user data", HttpStatus.BAD_REQUEST);
        }
    }
}
