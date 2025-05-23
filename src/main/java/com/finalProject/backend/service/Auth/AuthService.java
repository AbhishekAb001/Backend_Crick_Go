package com.finalProject.backend.service.Auth;

import com.finalProject.backend.model.authModels.LoginModel;
import com.finalProject.backend.model.authModels.SignupModel;
import com.finalProject.backend.model.authModels.Users;
import com.finalProject.backend.repository.AuthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    public List<Users> getAllUsers() {
        return authRepository.findAll();
    }

    public void register(SignupModel registerCred) {
        Users user = new Users();
        user.setUsername(registerCred.getUsername());
        user.setPassword(new BCryptPasswordEncoder(12).encode(registerCred.getPassword()));
        user.setEmail(registerCred.getEmail());
        user.setName(registerCred.getFullName());
        authRepository.save(user);
    }

    public ResponseEntity<?> verifyLogin(LoginModel loginCred) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginCred.getUsername(), loginCred.getPassword()));

            System.out.println("Authentication: " + authentication);
            if (authentication.isAuthenticated()) {
                Map<String,Object> response = new HashMap<>();
                Users user = authRepository.findByUsername(loginCred.getUsername());
                if (user != null){

                    response.put("userId", user.getUserId());
                    response.put("username", user.getUsername());
                    response.put("name", user.getName());
                    response.put("email", user.getEmail());
                    response.put("imgUrl", user.getImgUrl());
                    response.put("roleType", user.getRoleType());
                    response.put("playCount", user.getPlayCount());
                    response.put("totalRun", user.getTotalRun());
                    response.put("runRate", user.getRunRate());
                    response.put("phoneNumber", user.getPhoneNumber());
                    response.put("role", user.getRole());
                    response.put("token", jwtService.generateToken(loginCred.getUsername()));
                }else{
                    return ResponseEntity.status(404).body("User not found");
                }

                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            if (e instanceof org.springframework.security.authentication.BadCredentialsException) {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        }
        return ResponseEntity.status(401).body("Authentication failed");

    }

}