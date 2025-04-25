package com.finalProject.backend.service;

import java.util.List;

import com.finalProject.backend.model.authModels.UpdatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finalProject.backend.model.authModels.Users;
import com.finalProject.backend.repository.AuthRepository;

@Service
public class ProfileService {

    @Autowired
    AuthRepository authRepository;

    public ResponseEntity<?> getUser(String username) {
        try {
            Users user = authRepository.findByUsername(username);
            if (user == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching user: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllUsers() {
        try {
            List<Users> users = authRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching users: " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateUser(UpdatedUser user) {
        try {
            Users existingUser = authRepository.findByUsername(user.getUsername());
            if (existingUser == null) {
                return ResponseEntity.notFound().build();
            } else {
                // Check if any fields are actually different
                boolean hasChanges = !existingUser.getName().equals(user.getName()) ||
                        !existingUser.getEmail().equals(user.getEmail()) ||
                        !existingUser.getPhoneNumber().equals(user.getPhoneNumber()) ||
                        !existingUser.getRoleType().equals(user.getRoleType()) ||
                        !existingUser.getImgUrl().equals(user.getImgUrl());
                System.out.println(user.getImgUrl());
                if (hasChanges) {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPhoneNumber(user.getPhoneNumber());
                    existingUser.setRoleType(user.getRoleType());
                    existingUser.setImgUrl(user.getImgUrl());

                    System.out.println(existingUser.getImgUrl());
                } else {
                    return ResponseEntity.ok("No changes detected");
                }
                authRepository.save(existingUser);
                return ResponseEntity.ok(existingUser);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error updating user: " + e.getMessage());
        }
    }
}
