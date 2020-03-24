package domain;

import exceptions.eigenexcepties.OnjuistWachtwoordExceptie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EigenaarTest {

    private Eigenaar eigenaarUnderTest;

    @BeforeEach
    void setUp() {
        eigenaarUnderTest = new Eigenaar();
    }

    @Test
    void testSetIngelogd() throws Exception {
        // Setup
        final Eigenaar eigenaar = new Eigenaar();

        // Run the test
        eigenaarUnderTest.setIngelogd(eigenaar);

        // Verify the results
    }

    @Test
    void testSetIngelogd_ThrowsOnjuistWachtwoordExceptie() {
        // Setup
        final Eigenaar eigenaar = new Eigenaar();

        // Run the test
        assertThrows(OnjuistWachtwoordExceptie.class, () -> {
            eigenaarUnderTest.setIngelogd(eigenaar);
        });
    }

    @Test
    void testMaakAfspeellijst() {
        // Setup
        final Afspeellijst afspeellijst = new Afspeellijst();

        // Run the test
        eigenaarUnderTest.maakAfspeellijst(afspeellijst);

        // Verify the results
    }

    @Test
    void testWijzigAfspeellijst() {
        // Setup
        final Afspeellijst afspeellijst = new Afspeellijst();

        // Run the test
        eigenaarUnderTest.wijzigAfspeellijst(afspeellijst);

        // Verify the results
    }
}
