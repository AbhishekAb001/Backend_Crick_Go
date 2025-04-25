package com.finalProject.backend.controller.Tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalProject.backend.model.Tournament.TeamsModel;
import com.finalProject.backend.service.Tournament.TournamentTeamService;

@RestController
@RequestMapping("/tournament/team")
public class TournamentTeamController {

    @Autowired
    private TournamentTeamService tournamentTeamService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTeams(@RequestParam String tournamentId) {
        try {
            return tournamentTeamService.getAllTeams(tournamentId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error fetching teams: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody TeamsModel team) {
        try {
            System.out.println(team.getTeamMemberIds());
            return tournamentTeamService.addTeam(team);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding team: " + e.getMessage());
        }
    }

    @PostMapping("/members/add")
    public ResponseEntity<?> addTeamMember(@RequestParam String teamId, @RequestParam String memberId) {
        try {
            return tournamentTeamService.addTeamMember(teamId, memberId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error adding team member: " + e.getMessage());
        }
    }

}
