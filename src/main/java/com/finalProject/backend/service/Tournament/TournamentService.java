package com.finalProject.backend.service.Tournament;

import com.finalProject.backend.model.Tournament.TournamentAddModel;
import com.finalProject.backend.repository.Tournament.TournamentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public ResponseEntity<?> createTournament(TournamentAddModel tournament) {
        try {
            if (tournament.getName() == null || tournament.getName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Tournament name is required");
            }

            TournamentAddModel tournamentObj = new TournamentAddModel();
            // Basic info
            tournamentObj.setName(tournament.getName());
            tournamentObj.setDescription(tournament.getDescription());
            tournamentObj.setImageUrl(tournament.getImageUrl());
            tournamentObj.setOrganizer(tournament.getOrganizer());
            tournamentObj.setVenue(tournament.getVenue());
            tournamentObj.setPrizePool(tournament.getPrizePool());

            // Lists and Maps
            tournamentObj.setRules(tournament.getRules());
            tournamentObj.setTimelines(tournament.getTimelines());

            // Dates
            tournamentObj.setStartDate(tournament.getStartDate());
            tournamentObj.setEndDate(tournament.getEndDate());

            // Contact info
            tournamentObj.setLocation(tournament.getLocation());
            tournamentObj.setEmail(tournament.getEmail());
            tournamentObj.setPhone(tournament.getPhone());
            tournamentObj.setWebsite(tournament.getWebsite());
            // Status
            tournamentObj.setStatus(tournament.getStatus());
            // Save to database
            TournamentAddModel savedTournament = tournamentRepository.save(tournamentObj);
            return ResponseEntity.ok(savedTournament);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error creating tournament: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllTournaments() {
        try {
            return ResponseEntity.ok(tournamentRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching tournaments: " + e.getMessage());
        }
    }

    public ResponseEntity<?> addPhotos(String tournamentId, List<String> photoUrls) {
        try {
            TournamentAddModel tournament = tournamentRepository.findById(tournamentId)
                    .orElseThrow(() -> new RuntimeException("Tournament not found"));
            if (tournament.getPhotos() != null) {
                tournament.getPhotos().addAll(photoUrls);
            } else {
                tournament.setPhotos(photoUrls);
            }
            tournamentRepository.save(tournament);
            return ResponseEntity.ok(tournament);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding photos: " + e.getMessage());
        }
    }
}