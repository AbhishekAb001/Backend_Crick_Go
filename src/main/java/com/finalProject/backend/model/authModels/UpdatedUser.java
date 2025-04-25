package com.finalProject.backend.model.authModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedUser {
   private String username;
   private String name;
   private String email;
   private String phoneNumber;
   private String imgUrl;
    private String roleType;


}
