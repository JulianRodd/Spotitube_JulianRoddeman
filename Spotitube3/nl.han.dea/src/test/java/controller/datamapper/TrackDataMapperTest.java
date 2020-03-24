package controller.datamapper;

import controller.dtos.TrackDTO;
import domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrackDataMapperTest {

    private TrackDataMapper trackDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        trackDataMapperUnderTest = new TrackDataMapper();
    }

    @Test
    void testMapToDomain() {
        // Setup
        final TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(0);
        trackDTO.setTitle("title");
        trackDTO.setPerformer("performer");
        trackDTO.setDuration(0);
        trackDTO.setAlbum("album");
        trackDTO.setPlaycount(0);
        trackDTO.setPublicationDate("publicationDate");
        trackDTO.setDescription("description");
        trackDTO.setOfflineAvailable(false);

        // Run the test
        final Track result = trackDataMapperUnderTest.mapToDomain(trackDTO);

        // Verify the results
    }

    @Test
    void testMapToDTO() {
        // Setup
        final Track track = null;

        // Run the test
        final TrackDTO result = trackDataMapperUnderTest.mapToDTO(track);

        // Verify the results
    }
}
