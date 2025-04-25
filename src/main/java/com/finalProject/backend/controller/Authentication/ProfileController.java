package com.finalProject.backend.controller.Authentication;

import java.util.List;

import com.finalProject.backend.model.authModels.UpdatedUser;
import com.finalProject.backend.model.authModels.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finalProject.backend.service.ProfileService;

@RestController
@RequestMapping("/user")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUser(@RequestParam String username) {
        try {
            return profileService.getUser(username);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching user: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        try {
            return profileService.getAllUsers();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching users: " + e.getMessage());
        }
    }

    @PatchMapping("/patch")
    public ResponseEntity<?> updateUser(@RequestBody UpdatedUser user) {
       try {
              return profileService.updateUser(user);
         } catch (Exception e) {
              return ResponseEntity.internalServerError()
                     .body("Error updating user: " + e.getMessage());
       }

}

}
