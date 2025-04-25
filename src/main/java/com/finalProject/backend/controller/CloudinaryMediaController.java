package com.finalProject.backend.controller;

import com.finalProject.backend.service.CloudinaryImageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/media")
public class CloudinaryMediaController {

    @Autowired
    private CloudinaryImageImpl cloudinaryImageImpl;

    @PostMapping("/uploadImg")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> uploadResult = (Map<String, Object>) cloudinaryImageImpl.upload(image);
            return ResponseEntity.ok(uploadResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<?> uploadMultipleImages(@RequestParam("images") List<MultipartFile> images) {
        try {
            List<Map<String, Object>> uploadResults = cloudinaryImageImpl.uploadMultiple(images);
            return ResponseEntity.ok(uploadResults);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading images");
        }
    }
}
