package com.finalProject.backend.model.Tournament;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "tournament_gallery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryModel {

    @Id
    private String photosId;

    @Column(nullable = false)
    private String tournamentId;

    @ElementCollection
    @CollectionTable(
        name = "gallery_photos",
        joinColumns = @JoinColumn(name = "photos_id")
    )
    @Column(name = "photo_url")
    private List<String> photos;

    @PrePersist
    public void prePersist() {
        if (this.photosId == null) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            this.photosId = "PHOTO" + timestamp;
        }
    }
}