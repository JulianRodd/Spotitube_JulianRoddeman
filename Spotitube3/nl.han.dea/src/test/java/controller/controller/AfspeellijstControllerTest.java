package controller.controller;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;

class AfspeellijstControllerTest {

    private AfspeellijstController afspeellijstControllerUnderTest;

    @BeforeEach
    void setUp() {
        afspeellijstControllerUnderTest = new AfspeellijstController();
    }

    @Test
    void testAlleAfspeellijsten() {
        // Setup

        // Run the test
        final Response result = afspeellijstControllerUnderTest.alleAfspeellijsten("token");

        // Verify the results
    }

    @Test
    void testVerwijderAfspeellijst() {
        // Setup

        // Run the test
        final Response result = afspeellijstControllerUnderTest.verwijderAfspeellijst(0, "token");

        // Verify the results
    }

    @Test
    void testVoegAfspeellijstToe() {
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
        final Response result = afspeellijstControllerUnderTest.voegAfspeellijstToe(afspeellijstDTO, "token");

        // Verify the results
    }

    @Test
    void testWijzigAfspeellijstNaam() {
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
        final Response result = afspeellijstControllerUnderTest.wijzigAfspeellijstNaam(afspeellijstDTO, 0, "token");

        // Verify the results
    }

    @Test
    void testTrackVoorAfspeellijst() {
        // Setup

        // Run the test
        final Response result = afspeellijstControllerUnderTest.trackVoorAfspeellijst(0, "token");

        // Verify the results
    }

    @Test
    void testTrackVanAfspeellijst() {
        // Setup

        // Run the test
        final Response result = afspeellijstControllerUnderTest.trackVanAfspeellijst(0, "token");

        // Verify the results
    }

    @Test
    void testTrackVanAfspeellijst1() {
        // Setup

        // Run the test
        final Response result = afspeellijstControllerUnderTest.trackVanAfspeellijst(0, 0, "token");

        // Verify the results
    }

    @Test
    void testTrackAanPlaylistToevoegen() {
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
        final Response result = afspeellijstControllerUnderTest.trackAanPlaylistToevoegen(trackDTO, 0, "token");

        // Verify the results
    }
}
