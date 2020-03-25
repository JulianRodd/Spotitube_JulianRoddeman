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
        // Arrange

        // Act
         int result = afspeellijstUnderTest.berekenAfspeellijstLengte(0);

        // Assert
        assertEquals(0, result);
    }

    @Test
    void testVoegTracksToe() {
        // Arrange

        // Act


        // Assert
    }

    @Test
    void testVoegTrackToe() {
        // Arrange
         Track track = null;

        // Act


        // Assert
    }

    @Test
    void testUpdateTracks() {
        // Arrange

        // Act


        // Assert
    }

    @Test
    void testVerwijderTrack() {
        // Arrange
         Track track = null;

        // Act
        afspeellijstUnderTest.verwijderTrack(1,1);

        // Assert
    }

    @Test
    void testOpenTracksVoorAfspeellijst() {
        // Arrange

        // Act
         List<Track> result = afspeellijstUnderTest.openTracksAfspeellijst(0, true);

        // Assert
    }
}
