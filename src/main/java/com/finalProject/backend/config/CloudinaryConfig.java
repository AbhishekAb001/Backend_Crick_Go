package com.finalProject.backend.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary getCloudinary() {
        Map<String,Object> config = Map.of(
                "cloud_name", "dstf4op07",
                "api_key", "238997955455729",
                "api_secret", "2kerg4Kd_EdQDWap2FXl8mRHPXY",
                "secure", true
       );
        return new Cloudinary(config);
    }
}
