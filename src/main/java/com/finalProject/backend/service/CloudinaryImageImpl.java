package com.finalProject.backend.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryImageImpl {

    @Autowired
    private Cloudinary cloudinary;

    public Map upload(MultipartFile multipartFile) {
        try {
            return cloudinary.uploader().upload(multipartFile.getBytes(), Map.of());
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image", e);
        }
    }

    public List<Map<String, Object>> uploadMultiple(List<MultipartFile> files) {
        List<Map<String, Object>> results = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                Map<String, Object> options = new HashMap<>();
                options.put("folder", "Gallery");
                @SuppressWarnings("unchecked")
                Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(file.getBytes(), options);
                results.add(uploadResult);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload files", e);
        }
        return results;
    }

}
