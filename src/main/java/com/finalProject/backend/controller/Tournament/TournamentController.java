package com.finalProject.backend.controller.Tournament;

import com.finalProject.backend.model.Tournament.TournamentAddModel;
import com.finalProject.backend.service.Tournament.TournamentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournament")
@CrossOrigin(origins = "*")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllTournaments() {
        try {
            return tournamentService.getAllTournaments();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching tournaments: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTournament(@RequestBody TournamentAddModel tournament) {
        try {
            return tournamentService.createTournament(tournament);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error creating tournament: " + e.getMessage());
        }
    }

    @PostMapping("/addPhotos")
    public ResponseEntity<?> addPhotos(@RequestParam String tournamentId, @RequestBody List<String> photoUrls) {
        try {
            return tournamentService.addPhotos(tournamentId, photoUrls);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding photos: " + e.getMessage());
        }
    }

}