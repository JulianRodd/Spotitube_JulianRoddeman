package controller.datamapper;

import controller.dtos.AfspeellijstenDTO;
import domain.Afspeellijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SpotitubeDataMapperTest {

    private SpotitubeDataMapper spotitubeDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        spotitubeDataMapperUnderTest = new SpotitubeDataMapper();
    }

    @Test
    void testSetAfseellijst() {
        // Setup
        final Afspeellijst afseellijst = new Afspeellijst();

        // Run the test
        spotitubeDataMapperUnderTest.setAfseellijst(afseellijst);

        // Verify the results
    }

    @Test
    void testMapToDTO() {
        // Setup
        final List<Afspeellijst> afspeellijsten = Arrays.asList(new Afspeellijst());

        // Run the test
        final AfspeellijstenDTO result = spotitubeDataMapperUnderTest.mapToDTO(afspeellijsten);

        // Verify the results
    }
}
