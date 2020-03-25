package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.AfspeellijstenDTO;
import domain.Afspeellijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpotitubeDataMapperTest {

    private SpotitubeDataMapper spotitubeDataMapperUnderTest;
    private AfspeellijstDataMapper mockedAfspeellijstDataMapper;
    private Afspeellijst mockedAfspeellijst;
    @BeforeEach
    void setUp() {
        spotitubeDataMapperUnderTest = new SpotitubeDataMapper();
        this.mockedAfspeellijstDataMapper = mock(AfspeellijstDataMapper.class);
        this.mockedAfspeellijst = mock(Afspeellijst.class);
        this.spotitubeDataMapperUnderTest.setAfspeellijstDataMapper(mockedAfspeellijstDataMapper);
        this.spotitubeDataMapperUnderTest.setAfspeellijst(mockedAfspeellijst);
    }


    @Test
    void testMapToDTO() {
        // Arrange
        var afspeellijst = new Afspeellijst();
        var afspeellijstDTO = new AfspeellijstDTO();
        int lengte = 10;
        afspeellijstDTO.setId(0);
        afspeellijstDTO.setName("name");
        afspeellijstDTO.setOwner(true);
         List<Afspeellijst> afspeellijsten = Arrays.asList(afspeellijst);
            when(mockedAfspeellijstDataMapper.mapToDTO(afspeellijst)).thenReturn(afspeellijstDTO);
            when(mockedAfspeellijst.berekenAfspeellijstLengte(afspeellijst.getId())).thenReturn(lengte);
        // Act
         AfspeellijstenDTO actual = spotitubeDataMapperUnderTest.mapToDTO(afspeellijsten);
        // Assert
        assertEquals(actual.getPlaylists().get(0),afspeellijstDTO);
        assertEquals(actual.getLength(),lengte);
    }
}
