package com.finalProject.backend.model.authModels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Users {
    @Id
    private String userId;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String email;
    private String imgUrl = "https://res.cloudinary.com/dstf4op07/image/upload/w_1000,c_fill,ar_1:1,g_auto,r_max,bo_5px_solid_red,b_rgb:262c35/v1745481139/ChatGPT_Image_Apr_24_2025_01_22_00_PM_v6naow.png";
    private String roleType;
    private int playCount = 0;
    private int totalRun = 0;
    private double runRate = 0.0;

    private String phoneNumber = "0000000000";
    private List<String> role = List.of("USER");
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (this.userId == null) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            this.userId = "USER" + timestamp;
        }
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
