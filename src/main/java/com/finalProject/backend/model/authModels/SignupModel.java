package com.finalProject.backend.model.authModels;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupModel {
    private String username;
    private String password;
    private String email;
    private String fullName;

}
