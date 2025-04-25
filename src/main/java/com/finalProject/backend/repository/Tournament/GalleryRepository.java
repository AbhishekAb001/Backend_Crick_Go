package com.finalProject.backend.repository.Tournament;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalProject.backend.model.Tournament.GalleryModel;

public interface GalleryRepository extends JpaRepository<GalleryModel, String> {

    GalleryModel findByTournamentId(String tournamentId);
    
}
