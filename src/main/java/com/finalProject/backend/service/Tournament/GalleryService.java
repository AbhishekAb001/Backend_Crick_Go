package com.finalProject.backend.service.Tournament;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finalProject.backend.model.Tournament.GalleryModel;
import com.finalProject.backend.repository.Tournament.GalleryRepository;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;
    
    public ResponseEntity<?> getAllGallery(String tournamentId) {
        try {
            GalleryModel gallery = galleryRepository.findByTournamentId(tournamentId);
            if (gallery != null) {
                return ResponseEntity.ok(gallery);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching gallery: " + e.getMessage());
        }
    }

    public ResponseEntity<?> addGallery(GalleryModel gallery) {
        try {
            // Check if gallery exists for the tournament
            GalleryModel existingGallery = galleryRepository.findByTournamentId(gallery.getTournamentId());
            
            if (existingGallery != null) {
                // Update existing gallery
                List<String> existingPhotos = existingGallery.getPhotos();
                existingPhotos.addAll(gallery.getPhotos());
                existingGallery.setPhotos(existingPhotos);
                return ResponseEntity.ok(galleryRepository.save(existingGallery));
            } else {
                // Create new gallery
                return ResponseEntity.ok(galleryRepository.save(gallery));
            }
        } catch(Exception e) {
            return ResponseEntity.internalServerError().body("Error adding gallery: " + e.getMessage());
        }
    }
    
}
