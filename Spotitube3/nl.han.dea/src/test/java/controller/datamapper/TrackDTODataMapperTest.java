package controller.datamapper;

import controller.dtos.TrackDTO;
import domain.Lied;
import domain.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackDTODataMapperTest {

    private TrackDTODataMapper trackDTODataMapperUnderTest;

    @BeforeEach
    void setUp() {
        trackDTODataMapperUnderTest = new TrackDTODataMapper();
    }

    @Test
    void testMapToDomainMetLied() {
        // Arrange
         var trackDTO = new TrackDTO();
        trackDTO.setId(0);
        trackDTO.setTitle("title");
        trackDTO.setPerformer("performer");
        trackDTO.setDuration(0);
        trackDTO.setAlbum("album");
        trackDTO.setOfflineAvailable(false);

        // Act
        var actual = trackDTODataMapperUnderTest.mapToDomain(trackDTO);

        // Assert
        assertEquals(actual.getId(), trackDTO.getId());
        assertEquals(actual.getTitel(),trackDTO.getTitle());
        assertEquals(actual.getPerformer(),trackDTO.getPerformer());
        assertEquals(actual.getAfspeelduur(),trackDTO.getDuration());
        assertEquals(((Lied)actual).getAlbum(),trackDTO.getAlbum());
        assertEquals(actual.isOfflineBeschikbaar(),trackDTO.isOfflineAvailable());

    }
    @Test
    void testMapToDomainMetVideo() {
        // Arrange
        var trackDTO = new TrackDTO();
        trackDTO.setId(0);
        trackDTO.setTitle("title");
        trackDTO.setPerformer("performer");
        trackDTO.setDuration(0);
        trackDTO.setPublicationDate("1-1-1");
        trackDTO.setDescription("leuk");
        trackDTO.setPlaycount(10);
        trackDTO.setOfflineAvailable(false);

        // Act
        var actual = trackDTODataMapperUnderTest.mapToDomain(trackDTO);

        // Assert
        assertEquals(actual.getId(), trackDTO.getId());
        assertEquals(actual.getTitel(),trackDTO.getTitle());
        assertEquals(actual.getPerformer(),trackDTO.getPerformer());
        assertEquals(actual.getAfspeelduur(),trackDTO.getDuration());
        assertEquals(((Video)actual).getBeschrijving(),trackDTO.getDescription());
        assertEquals(((Video)actual).getPublicatieDatum(),trackDTO.getPublicationDate());
        assertEquals(((Video)actual).getAantalWeergaven(),trackDTO.getPlaycount());
        assertEquals(actual.isOfflineBeschikbaar(),trackDTO.isOfflineAvailable());

    }

    @Test
    void testMapToDTOMetLied() {
        // Arrange
        var track = new Lied(1, "a", null,2,true, "a", "a");

        // Act
        var actual = trackDTODataMapperUnderTest.mapToDTO(track);

        // Assert
        assertEquals(track.getId(), actual.getId());
        assertEquals(track.getTitel(),actual.getTitle());
        assertEquals(track.getPerformer(),actual.getPerformer());
        assertEquals(track.getAfspeelduur(),actual.getDuration());
        assertEquals(((Lied)track).getAlbum(),actual.getAlbum());
        assertEquals(track.isOfflineBeschikbaar(),actual.isOfflineAvailable());

    }
    @Test
    void testMapToDTOMetVideo() {
        // Arrange
        var track = new Video(1, "a", null,2,true, "a", "a", "a", 1);

        // Act
        var actual = trackDTODataMapperUnderTest.mapToDTO(track);


        // Assert
        assertEquals(track.getId(), actual.getId());
        assertEquals(track.getTitel(),actual.getTitle());
        assertEquals(track.getPerformer(),actual.getPerformer());
        assertEquals(track.getAfspeelduur(),actual.getDuration());
        assertEquals(((Video)track).getBeschrijving(),actual.getDescription());
        assertEquals(((Video)track).getPublicatieDatum(),actual.getPublicationDate());
        assertEquals(((Video)track).getAantalWeergaven(),actual.getPlaycount());
        assertEquals(track.isOfflineBeschikbaar(),actual.isOfflineAvailable());

    }
}
