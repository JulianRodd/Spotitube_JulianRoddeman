package controller.datamapper;

import controller.dtos.AfspeellijstDTO;
import controller.dtos.TrackDTO;
import domain.Afspeellijst;
import domain.Lied;
import domain.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AfspeellijstDataMapperTest {

    private AfspeellijstDataMapper afspeellijstDataMapperUnderTest;
    private TrackDataMapper mockedTrackDataMapper;

    @BeforeEach
    void setUp() {
        afspeellijstDataMapperUnderTest = new AfspeellijstDataMapper();
        this.mockedTrackDataMapper = mock(TrackDataMapper.class);
        this.afspeellijstDataMapperUnderTest.setTrackDataMapper(mockedTrackDataMapper);
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
        when(mockedTrackDataMapper.mapToDomain(trackDTO)).thenReturn(track);
        // Act
         Afspeellijst actual = afspeellijstDataMapperUnderTest.mapToDomain(afspeellijstDTO);
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
        when(mockedTrackDataMapper.mapToDTO(track)).thenReturn(trackDTO);
        // Act
        AfspeellijstDTO actual = afspeellijstDataMapperUnderTest.mapToDTO(afspeellijst);
        // Assert
        assertEquals(actual.getName(), afspeellijst.getNaam());
        assertEquals(actual.getId(), afspeellijst.getId());
        assertEquals(actual.getOwner(), true);
        assertEquals(actual.getTracks().get(0),trackDTO);
    }
}
