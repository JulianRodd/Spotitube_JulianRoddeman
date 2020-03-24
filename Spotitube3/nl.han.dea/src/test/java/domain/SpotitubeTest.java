package domain;

import exceptions.eigenexcepties.VerkeerdeTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SpotitubeTest {

    private Spotitube spotitubeUnderTest;

    @BeforeEach
    void setUp() {
        spotitubeUnderTest = new Spotitube();
    }

    @Test
    void testOpenAfspeellijst() {
        // Setup

        // Run the test
        final Afspeellijst result = spotitubeUnderTest.openAfspeellijst(0);

        // Verify the results
    }

    @Test
    void testOpenOverzicht() {
        // Setup

        // Run the test
        final List<Afspeellijst> result = spotitubeUnderTest.openOverzicht();

        // Verify the results
    }

    @Test
    void testToonTrackOverzicht() {
        // Setup

        // Run the test
        final List<Track> result = spotitubeUnderTest.toonTrackOverzicht();

        // Verify the results
    }

    @Test
    void testVerwijderAfspeellijst() {
        // Setup

        // Run the test
        spotitubeUnderTest.verwijderAfspeellijst(0);

        // Verify the results
    }

    @Test
    void testGetEigenaar() {
        // Setup

        // Run the test
        final Eigenaar result = spotitubeUnderTest.getEigenaar("token");

        // Verify the results
    }

    @Test
    void testGetEigenaar_ThrowsVerkeerdeTokenException() {
        // Setup

        // Run the test
        assertThrows(VerkeerdeTokenException.class, () -> {
            spotitubeUnderTest.getEigenaar("token");
        });
    }
}
