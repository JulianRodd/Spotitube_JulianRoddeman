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
        // Arrange

        // Act
         Afspeellijst result = spotitubeUnderTest.openAfspeellijst(0);

        // Assert
    }

    @Test
    void testOpenOverzicht() {
        // Arrange

        // Act
         List<Afspeellijst> result = spotitubeUnderTest.openOverzicht();

        // Assert
    }

    @Test
    void testToonTrackOverzicht() {
        // Arrange

        // Act
         List<Track> result = spotitubeUnderTest.toonTrackOverzicht();

        // Assert
    }

    @Test
    void testVerwijderAfspeellijst() {
        // Arrange

        // Act
        spotitubeUnderTest.verwijderAfspeellijst(0);

        // Assert
    }

    @Test
    void testGetEigenaar() {
        // Arrange

        // Act
         Eigenaar result = spotitubeUnderTest.getEigenaar("token");

        // Assert
    }

    @Test
    void testGetEigenaar_ThrowsVerkeerdeTokenException() {
        // Arrange

        // Act
        assertThrows(VerkeerdeTokenException.class, () -> {
            spotitubeUnderTest.getEigenaar("token");
        });
    }
}
