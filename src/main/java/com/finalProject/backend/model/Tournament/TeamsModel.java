package com.finalProject.backend.model.Tournament;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "tournament_teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamsModel {
    @Id
    private String teamId;

    @Column(nullable = false)
    private String tournamentId;

    @Column(nullable = false)
    private String teamName;

    private String teamLogo;

    @Column(nullable = false)
    private String teamCaptain;

    @ElementCollection
    @CollectionTable(name = "team_members", joinColumns = @JoinColumn(name = "team_id"))
    @Column(name = "member_id")
    private List<String> teamMemberIds;

    @Column(length = 500)
    private String teamDescription;

    @Column(nullable = false)
    private String teamStatus = "PENDING";

    @Column(nullable = false)
    private String teamEmail;

    @Column(nullable = false)
    private String teamPhone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "matches_played", columnDefinition = "INTEGER DEFAULT 0")
    private int matchesPlayed;

    @Column(name = "wins", columnDefinition = "INTEGER DEFAULT 0")
    private int wins;

    @Column(name = "losses", columnDefinition = "INTEGER DEFAULT 0")
    private int losses;

    @Column(name = "draws", columnDefinition = "INTEGER DEFAULT 0")
    private int draws;

    @Column(name = "ties", columnDefinition = "INTEGER DEFAULT 0")
    private int ties;

    @Column(name = "no_results", columnDefinition = "INTEGER DEFAULT 0")
    private int noResults;

    @Column(name = "net_run_rate", columnDefinition = "DECIMAL(5,3) DEFAULT 0.000")
    private double netRunRate;

    @Column(name = "points", columnDefinition = "INTEGER DEFAULT 0")
    private int points;

    // Add JSON column mapping for result_history
    @Column(columnDefinition = "json")
    private String result_history;

    // Add getter and setter
    public String getResultHistory() {
        return result_history;
    }

    public void setResultHistory(String result_history) {
        this.result_history = result_history;
    }

    @PrePersist
    public void prePersist() {
        if (this.teamId == null) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            this.teamId = "TEAM" + timestamp;
        }
        this.createdAt = LocalDateTime.now();
        // Set default result history if null
        if (this.result_history == null) {
            this.result_history = "[\"NP\", \"NP\", \"NP\", \"NP\", \"NP\"]";
        }

    }

    // Getters and Setters
    public List<String> getTeamMemberIds() {
        return teamMemberIds;
    }

    public void setTeamMemberIds(List<String> teamMemberIds) {
        this.teamMemberIds = teamMemberIds;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getTeamCaptain() {
        return teamCaptain;
    }

    public void setTeamCaptain(String teamCaptain) {
        this.teamCaptain = teamCaptain;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail;
    }

    public String getTeamPhone() {
        return teamPhone;
    }

    public void setTeamPhone(String teamPhone) {
        this.teamPhone = teamPhone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getNoResults() {
        return noResults;
    }

    public void setNoResults(int noResults) {
        this.noResults = noResults;
    }

    public double getNetRunRate() {
        return netRunRate;
    }

    public void setNetRunRate(double netRunRate) {
        this.netRunRate = netRunRate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}