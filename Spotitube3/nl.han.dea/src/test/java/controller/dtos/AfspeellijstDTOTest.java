package controller.dtos;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

class AfspeellijstDTOTest {

    private AfspeellijstDTO afspeellijstDTOUnderTest;

    @BeforeEach
    void setUp() {
        afspeellijstDTOUnderTest = new AfspeellijstDTO();
        afspeellijstDTOUnderTest.tracks = Arrays.asList(new TrackDTO());
    }
}
