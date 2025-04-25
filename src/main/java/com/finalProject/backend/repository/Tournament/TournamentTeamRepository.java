package com.finalProject.backend.repository.Tournament;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalProject.backend.model.Tournament.TeamsModel;

public interface TournamentTeamRepository extends JpaRepository<TeamsModel, String> {

    List<TeamsModel> findByTournamentId(String tournamentId);

}
