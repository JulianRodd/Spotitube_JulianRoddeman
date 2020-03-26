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

class AfspeellijstenDTODataMapperTest {
    private static final int ID = 1;
    private AfspeellijstenDTODataMapper afspeellijstenDTODataMapperUnderTest;
    private AfspeellijstDTODataMapper mockedAfspeellijstDTODataMapper;
    private Afspeellijst mockedAfspeellijst;
    @BeforeEach
    void setUp() {
        afspeellijstenDTODataMapperUnderTest = new AfspeellijstenDTODataMapper();
        this.mockedAfspeellijstDTODataMapper = mock(AfspeellijstDTODataMapper.class);
        this.mockedAfspeellijst = mock(Afspeellijst.class);
        this.afspeellijstenDTODataMapperUnderTest.setAfspeellijstDTODataMapper(mockedAfspeellijstDTODataMapper);
        this.afspeellijstenDTODataMapperUnderTest.setAfspeellijst(mockedAfspeellijst);
    }


    @Test
    void testMapToDTO() {
        // Arrange
        var afspeellijst = new Afspeellijst();
        var afspeellijstDTO = new AfspeellijstDTO();
        int lengte = 10;
        afspeellijstDTO.setId(ID);
        afspeellijstDTO.setName("name");
        afspeellijstDTO.setOwner(true);
         List<Afspeellijst> afspeellijsten = Arrays.asList(afspeellijst);
            when(mockedAfspeellijstDTODataMapper.mapToDTO(afspeellijst)).thenReturn(afspeellijstDTO);
            when(mockedAfspeellijst.berekenAfspeellijstLengte(afspeellijst.getId())).thenReturn(lengte);
        // Act
         AfspeellijstenDTO actual = afspeellijstenDTODataMapperUnderTest.mapToDTO(afspeellijsten);
        // Assert
        assertEquals(actual.getPlaylists().get(0),afspeellijstDTO);
        assertEquals(actual.getLength(),lengte);
    }
}
