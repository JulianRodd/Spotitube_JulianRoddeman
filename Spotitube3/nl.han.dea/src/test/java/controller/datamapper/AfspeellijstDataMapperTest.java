package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.TrackDTO;
import domain.Afspeellijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class AfspeellijstDataMapperTest {

    private AfspeellijstDataMapper afspeellijstDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        afspeellijstDataMapperUnderTest = new AfspeellijstDataMapper();
    }

    @Test
    void testMapToDomain() {
        // Setup
        final AfspeellijstDTO afspeellijstDTO = new AfspeellijstDTO();
        afspeellijstDTO.setId(0);
        afspeellijstDTO.setName("name");
        afspeellijstDTO.setOwner(false);
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
        afspeellijstDTO.setTracks(Arrays.asList(trackDTO));

        // Run the test
        final Afspeellijst result = afspeellijstDataMapperUnderTest.mapToDomain(afspeellijstDTO);

        // Verify the results
    }

    @Test
    void testMapToDTO() {
        // Setup
        final Afspeellijst afspeellijst = new Afspeellijst();

        // Run the test
        final AfspeellijstDTO result = afspeellijstDataMapperUnderTest.mapToDTO(afspeellijst);

        // Verify the results
    }
}
