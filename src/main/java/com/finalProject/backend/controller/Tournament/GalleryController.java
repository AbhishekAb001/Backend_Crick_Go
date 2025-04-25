package com.finalProject.backend.controller.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalProject.backend.model.Tournament.GalleryModel;
import com.finalProject.backend.service.Tournament.GalleryService;

@RestController
@RequestMapping("/tournament/gallary")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllTournaments(@RequestParam String tournamentId) {
        try {
            System.out.println("tournamentId: " + tournamentId);
            return ResponseEntity.ok(galleryService.getAllGallery(tournamentId)); 
        }catch (Exception e) {
            return ResponseEntity.internalServerError()
                   .body("Error fetching tournaments: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addGallery(@RequestBody GalleryModel gallery) {
        try {
            return ResponseEntity.ok(galleryService.addGallery(gallery));
        }catch (Exception e) {
            return ResponseEntity.internalServerError()
                  .body("Error fetching tournaments: " + e.getMessage());
        }
    }
}
