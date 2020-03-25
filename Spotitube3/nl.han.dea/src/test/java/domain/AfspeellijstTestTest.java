package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AfspeellijstTestTest {

    private AfspeellijstTest afspeellijstTestUnderTest;

    @BeforeEach
    void setUp() {
        afspeellijstTestUnderTest = new AfspeellijstTest();
    }

    @Test
    void testSetUp() {
        // Arrange

        // Act
        afspeellijstTestUnderTest.setUp();

        // Assert
    }

    @Test
    void testTestBerekenAfspeellijstLengte() {
        // Arrange

        // Act
        afspeellijstTestUnderTest.testBerekenAfspeellijstLengte();

        // Assert
    }

    @Test
    void testTestVoegTracksToe() {
        // Arrange

        // Act
        afspeellijstTestUnderTest.testVoegTracksToe();

        // Assert
    }

    @Test
    void testTestVoegTrackToe() {
        // Arrange

        // Act
        afspeellijstTestUnderTest.testVoegTrackToe();

        // Assert
    }

    @Test
    void testTestUpdateTracks() {
        // Arrange

        // Act
        afspeellijstTestUnderTest.testUpdateTracks();

        // Assert
    }

    @Test
    void testTestVerwijderTrack() {
        // Arrange

        // Act
        afspeellijstTestUnderTest.testVerwijderTrack();

        // Assert
    }

    @Test
    void testTestOpenTracksVoorAfspeellijst() {
        // Arrange

        // Act
        afspeellijstTestUnderTest.testOpenTracksVoorAfspeellijst();

        // Assert
    }
}
