package com.finalProject.backend.repository.Tournament;

import com.finalProject.backend.model.Tournament.TournamentAddModel;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TournamentRepository extends JpaRepository<TournamentAddModel, String> {

    @Modifying
    @Query("UPDATE TournamentAddModel t SET t.totalTeams = t.totalTeams + 1 WHERE t.tournamentId = :tournamentId")
    @Transactional
    void updateTeamCount(@Param("tournamentId") String tournamentId);
}
