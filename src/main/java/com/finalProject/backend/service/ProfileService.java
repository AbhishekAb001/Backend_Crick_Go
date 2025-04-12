package com.finalProject.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalProject.backend.model.authModels.Users;
import com.finalProject.backend.repository.AuthRepository;

@Service
public class ProfileService {
    
    @Autowired
    AuthRepository authRepository;

    public Users getUser(String username) {
        Users user = authRepository.findByUsername(username);
        if(user == null){
            return null;
        }
        return user;
    }
}
