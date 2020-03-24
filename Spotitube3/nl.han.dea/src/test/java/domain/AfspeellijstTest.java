package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AfspeellijstTest {

    private Afspeellijst afspeellijstUnderTest;

    @BeforeEach
    void setUp() {
        afspeellijstUnderTest = new Afspeellijst();
    }

    @Test
    void testBerekenAfspeellijstLengte() {
        // Setup

        // Run the test
        final int result = afspeellijstUnderTest.berekenAfspeellijstLengte(0);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testVoegTracksToe() {
        // Setup

        // Run the test
        afspeellijstUnderTest.voegTracksToe();

        // Verify the results
    }

    @Test
    void testVoegTrackToe() {
        // Setup
        final Track track = null;

        // Run the test
        afspeellijstUnderTest.voegTrackToe(track);

        // Verify the results
    }

    @Test
    void testUpdateTracks() {
        // Setup

        // Run the test
        afspeellijstUnderTest.updateTracks();

        // Verify the results
    }

    @Test
    void testVerwijderTrack() {
        // Setup
        final Track track = null;

        // Run the test
        afspeellijstUnderTest.verwijderTrack(track);

        // Verify the results
    }

    @Test
    void testOpenTracksVoorAfspeellijst() {
        // Setup

        // Run the test
        final List<Track> result = afspeellijstUnderTest.openTracksVoorAfspeellijst(0);

        // Verify the results
    }
}
