package com.finalProject.backend.repository;

import com.finalProject.backend.model.authModels.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Users, String> {

    public Users findByUsername(String username);
}
