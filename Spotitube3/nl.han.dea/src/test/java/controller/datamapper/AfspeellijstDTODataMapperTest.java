package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.TrackDTO;
import domain.Afspeellijst;
import domain.Lied;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AfspeellijstDTODataMapperTest {

    private AfspeellijstDTODataMapper afspeellijstDTODataMapperUnderTest;
    private TrackDTODataMapper mockedTrackDTODataMapper;

    @BeforeEach
    void setUp() {
        afspeellijstDTODataMapperUnderTest = new AfspeellijstDTODataMapper();
        this.mockedTrackDTODataMapper = mock(TrackDTODataMapper.class);
        this.afspeellijstDTODataMapperUnderTest.setTrackDTODataMapper(mockedTrackDTODataMapper);
    }

    @Test
    void testMapToDomain() {
        // Arrange
         var afspeellijstDTO = new AfspeellijstDTO();
        afspeellijstDTO.setId(0);
        afspeellijstDTO.setName("name");
        afspeellijstDTO.setOwner(false);
         var trackDTO = new TrackDTO();
        var track = new Lied(1, "a", null,2,true, "a", "a");
        afspeellijstDTO.setTracks(Arrays.asList(trackDTO));
        when(mockedTrackDTODataMapper.mapToDomain(trackDTO)).thenReturn(track);
        // Act
         Afspeellijst actual = afspeellijstDTODataMapperUnderTest.mapToDomain(afspeellijstDTO);
        // Assert
        assertEquals(actual.getNaam(), afspeellijstDTO.getName());
        assertEquals(actual.getId(), afspeellijstDTO.getId());
        assertEquals(actual.getEigenaar(), null);
        assertEquals(actual.getTracks().get(0),track);

    }

    @Test
    void testMapToDTO() {
        // Arrange
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(0);
        afspeellijst.setNaam("name");
        afspeellijst.setEigenaar("gebruiker0");
        var track = new Lied(1, "a", null,2,true, "a", "a");
        var trackDTO = new TrackDTO();
        afspeellijst.setTracks(Arrays.asList(track));
        when(mockedTrackDTODataMapper.mapToDTO(track)).thenReturn(trackDTO);
        // Act
        AfspeellijstDTO actual = afspeellijstDTODataMapperUnderTest.mapToDTO(afspeellijst);
        // Assert
        assertEquals(actual.getName(), afspeellijst.getNaam());
        assertEquals(actual.getId(), afspeellijst.getId());
        assertEquals(actual.getOwner(), true);
        assertEquals(actual.getTracks().get(0),trackDTO);
    }
}
