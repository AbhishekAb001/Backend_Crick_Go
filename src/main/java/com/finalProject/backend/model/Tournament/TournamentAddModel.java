package com.finalProject.backend.model.Tournament;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tournament")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TournamentAddModel {
    @Id
    private String tournamentId;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String imageUrl;
    private String organizer;
    private String venue;
    private double prizePool;
    private String type;

    @ElementCollection
    @CollectionTable(name = "tournament_rules", joinColumns = @JoinColumn(name = "tournament_id"))
    @Column(name = "rule")
    private List<String> rules;

    @ElementCollection
    @CollectionTable(name = "tournament_timelines", joinColumns = @JoinColumn(name = "tournament_id"))
    @MapKeyColumn(name = "timeline_key")
    @Column(name = "timeline_value")
    private Map<String, String> timelines;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String location;
    private String email;
    private String phone;
    List<String> photos = null;
    private String website;
    private String status;
    private String instagram = "";
    private String twitter = "";
    private String facebook = "";
    private int totalMatches = 0;
    private int totalTeams = 0;
    private boolean isGenerated = false;

    @PrePersist
    public void generateId() {
        if (this.tournamentId == null) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            this.tournamentId = "TOUR" + timestamp;
        }
    }

    // Getters and setters are handled by Lombok @Data annotation

}