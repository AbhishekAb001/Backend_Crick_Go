package com.finalProject.backend.service.Tournament;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finalProject.backend.model.Tournament.TeamsModel;
import com.finalProject.backend.repository.Tournament.TournamentRepository;
import com.finalProject.backend.repository.Tournament.TournamentTeamRepository;

@Service
public class TournamentTeamService {
    @Autowired
    private TournamentTeamRepository tournamentTeamRepository;
    @Autowired
    private TournamentRepository tournamentRepository;

    public ResponseEntity<?> getAllTeams(String tournamentId) {
        try {
            return ResponseEntity.ok(tournamentTeamRepository.findByTournamentId(tournamentId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("error while getting teams" + e.getMessage());
        }
    }

    public ResponseEntity<?> addTeam(TeamsModel team) {
        try {
            // Save the team first
            TeamsModel savedTeam = tournamentTeamRepository.save(team);
            
            // Update tournament team count
            try {
                tournamentRepository.updateTeamCount(team.getTournamentId());
            } catch (Exception e) {
                // If update fails, log error but don't fail the entire operation
                return ResponseEntity.internalServerError().body("error while updating team count: " + e.getMessage());
            }
            
            return ResponseEntity.ok(savedTeam);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("error while adding team: " + e.getMessage());
        }
    }

    public ResponseEntity<?> addTeamMember(String teamId, String memberId) {
        try {
            TeamsModel team = tournamentTeamRepository.findById(teamId)
                    .orElseThrow(() -> new RuntimeException("Team not found"));

            List<String> members = team.getTeamMemberIds();
            if (members == null) {
                members = new ArrayList<>();
            }
            if (!members.contains(memberId)) {
                members.add(memberId);
                team.setTeamMemberIds(members);
                tournamentTeamRepository.save(team);
            }
            return ResponseEntity.ok(team);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error adding team member: " + e.getMessage());
        }
    }

   
}
