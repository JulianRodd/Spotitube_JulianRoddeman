package controller;

import controller.datamapper.AfspeellijstDTODataMapper;
import controller.datamapper.AfspeellijstenDTODataMapper;
import controller.datamapper.TrackDTODataMapper;
import controller.dtos.AfspeellijstDTO;
import controller.dtos.AfspeellijstenDTO;
import controller.dtos.TrackDTO;
import domain.*;
import exceptions.eigenexcepties.VerkeerdeTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AfspeellijstControllerTest {
    private static final String TOKEN = "1234";
    private static final int ID = 1;
    private static final int HTTP_OK = 200;
    private AfspeellijstController afspeellijstControllerUnderTest;
    private AfspeellijstenDTODataMapper mockedAfspeellijstenDTODataMapper;
    private AfspeellijstDTODataMapper mockedAfspeellijstDTODataMapper;
    private TrackDTODataMapper mockedTrackDTODataMapper;
    private Spotitube mockedSpotitube;
    private Eigenaar mockedEigenaar;
    private Afspeellijst mockedAfspeellijst;

    @BeforeEach
    void setUp() {
        afspeellijstControllerUnderTest = new AfspeellijstController();
        this.mockedAfspeellijstenDTODataMapper = mock(AfspeellijstenDTODataMapper.class);
        this.mockedAfspeellijstDTODataMapper = mock(AfspeellijstDTODataMapper.class);
        this.mockedTrackDTODataMapper = mock(TrackDTODataMapper.class);
        this.mockedSpotitube = mock(Spotitube.class);
        this.mockedEigenaar = mock(Eigenaar.class);
        this.mockedAfspeellijst = mock(Afspeellijst.class);
        this.afspeellijstControllerUnderTest.setAfspeellijstenDTODataMapper(mockedAfspeellijstenDTODataMapper);
        ;
        this.afspeellijstControllerUnderTest.setAfspeellijstDTODataMapper(mockedAfspeellijstDTODataMapper);
        this.afspeellijstControllerUnderTest.setTrackDTODataMapper(mockedTrackDTODataMapper);
        this.afspeellijstControllerUnderTest.setSpotitube(mockedSpotitube);
        this.afspeellijstControllerUnderTest.setEigenaar(mockedEigenaar);
        this.afspeellijstControllerUnderTest.setAfspeellijst(mockedAfspeellijst);
    }

    @Test
    void testAlleAfspeellijstenReturndAlleAfspeellijsten() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        var afspeellijsten = new ArrayList<Afspeellijst>();
        var afspeellijstenDTO = new AfspeellijstenDTO();
        var afspeellijstDTO = new AfspeellijstDTO();
        afspeellijstenDTO.setLength(50);
        afspeellijstenDTO.setPlaylists(Arrays.asList(afspeellijstDTO));
        when(mockedSpotitube.openOverzicht()).thenReturn(afspeellijsten);
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(afspeellijsten)).thenReturn(afspeellijstenDTO);
        // Act
        Response result = afspeellijstControllerUnderTest.alleAfspeellijsten("TOKEN");
        // Assert
        assertEquals(afspeellijstenDTO, result.getEntity());
        assertEquals(HTTP_OK, result.getStatus());
    }

    @Test
    void testAlleAfspeellijstenThrowsVerkeerdeTokenExceptie() {
        // Arrange
        var afspeellijstenDTO = new AfspeellijstenDTO();
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(mockedSpotitube.openOverzicht())).thenReturn(afspeellijstenDTO);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);
        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.alleAfspeellijsten(TOKEN));
    }

    @Test
    void testVerwijderAfspeellijstReturndAlleAfspeellijsten() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        var afspeellijsten = new ArrayList<Afspeellijst>();
        var afspeellijstenDTO = new AfspeellijstenDTO();
        var afspeellijstDTO = new AfspeellijstDTO();
        afspeellijstenDTO.setLength(50);
        afspeellijstenDTO.setPlaylists(Arrays.asList(afspeellijstDTO));
        when(mockedSpotitube.openOverzicht()).thenReturn(afspeellijsten);
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(afspeellijsten)).thenReturn(afspeellijstenDTO);
        doNothing().when(mockedSpotitube).verwijderAfspeellijst(ID);
        // Act
        Response result = afspeellijstControllerUnderTest.verwijderAfspeellijst(ID, "TOKEN");
        // Assert
        assertEquals(afspeellijstenDTO, result.getEntity());
        assertEquals(HTTP_OK, result.getStatus());
    }

    @Test
    void testVerwijderAfspeellijstThrowsVerkeerdeTokenExceptie() {
        // Arrange
        var afspeellijstenDTO = new AfspeellijstenDTO();
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(mockedSpotitube.openOverzicht())).thenReturn(afspeellijstenDTO);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);
        doNothing().when(mockedSpotitube).verwijderAfspeellijst(ID);
        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.verwijderAfspeellijst(ID, TOKEN));
    }

    @Test
    void testVoegAfspeellijstToeReturndAlleAfspeellijsten() {
        // Arrange
        var afspeellijsten = new ArrayList<Afspeellijst>();
        var afspeellijstenDTO = new AfspeellijstenDTO();
        var afspeellijstDTO = new AfspeellijstDTO();
        var afspeellijst = new Afspeellijst();
        var eigenaar = new Eigenaar();
        afspeellijstenDTO.setLength(50);
        afspeellijstenDTO.setPlaylists(Arrays.asList(afspeellijstDTO));
        when(mockedSpotitube.openOverzicht()).thenReturn(afspeellijsten);
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(afspeellijsten)).thenReturn(afspeellijstenDTO);
        when(mockedAfspeellijstDTODataMapper.mapToDomain(afspeellijstDTO)).thenReturn(afspeellijst);
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(eigenaar);
        doNothing().when(mockedEigenaar).maakAfspeellijst(afspeellijst);
        // Act
        Response result = afspeellijstControllerUnderTest.voegAfspeellijstToe(afspeellijstDTO, TOKEN);
        // Assert
        assertEquals(afspeellijstenDTO, result.getEntity());
        assertEquals(HTTP_OK, result.getStatus());
    }

    @Test
    void testVoegAfspeellijstToeThrowsVerkeerdeTokenExceptie() {
        // Arrange
        var afspeellijstenDTO = new AfspeellijstenDTO();
        var afspeellijst = new Afspeellijst();
        var afspeellijstDTO = new AfspeellijstDTO();
        when(mockedAfspeellijstDTODataMapper.mapToDomain(afspeellijstDTO)).thenReturn(afspeellijst);
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(mockedSpotitube.openOverzicht())).thenReturn(afspeellijstenDTO);
        doNothing().when(mockedSpotitube).verwijderAfspeellijst(ID);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);
        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.voegAfspeellijstToe(afspeellijstDTO, TOKEN));
    }

    @Test
    void testWijzigAfspeellijstNaamReturndAlleAfspeellijsten() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        var afspeellijsten = new ArrayList<Afspeellijst>();
        var afspeellijstenDTO = new AfspeellijstenDTO();
        var afspeellijstDTO = new AfspeellijstDTO();
        var afspeellijst = new Afspeellijst();
        afspeellijstenDTO.setLength(50);
        afspeellijstenDTO.setPlaylists(Arrays.asList(afspeellijstDTO));
        when(mockedSpotitube.openOverzicht()).thenReturn(afspeellijsten);
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(afspeellijsten)).thenReturn(afspeellijstenDTO);
        when(mockedAfspeellijstDTODataMapper.mapToDomain(afspeellijstDTO)).thenReturn(afspeellijst);
        doNothing().when(mockedEigenaar).wijzigAfspeellijst(afspeellijst);

        // Act
        Response result = afspeellijstControllerUnderTest.wijzigAfspeellijstNaam(afspeellijstDTO, ID, TOKEN);

        // Assert
        assertEquals(afspeellijstenDTO, result.getEntity());
        assertEquals(HTTP_OK, result.getStatus());
    }

    @Test
    void testWijzigAfspeellijstNaamThrowsVerkeerdeTokenExceptie() {
        // Arrange
        var afspeellijstenDTO = new AfspeellijstenDTO();
        var afspeellijst = new Afspeellijst();
        var afspeellijstDTO = new AfspeellijstDTO();
        when(mockedAfspeellijstDTODataMapper.mapToDomain(afspeellijstDTO)).thenReturn(afspeellijst);
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(mockedSpotitube.openOverzicht())).thenReturn(afspeellijstenDTO);
        doNothing().when(mockedEigenaar).wijzigAfspeellijst(afspeellijst);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);
        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.wijzigAfspeellijstNaam(afspeellijstDTO, ID, TOKEN));
    }

    @Test
    void testTrackVoorAfspeellijstRoeptOpenTracksAfspeellijstAanBijId() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        // Act
        Response result = afspeellijstControllerUnderTest.trackVoorAfspeellijst(ID, TOKEN);
        // Assert
        verify(mockedAfspeellijst).openTracksAfspeellijst(ID,true);
    }
    @Test
    void testTrackVoorAfspeellijstRoeptToonTrackOverzichtAanBijGeenId() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        // Act
        Response result = afspeellijstControllerUnderTest.trackVoorAfspeellijst(0, TOKEN);
        // Assert
        verify(mockedSpotitube).toonTrackOverzicht();
    }
    @Test
    void testTrackVoorAfspeellijstThrowsVerkeerdeTokenExceptie() {
        // Arrange
        List<Track> tracks = new ArrayList<Track>();
        when(mockedSpotitube.toonTrackOverzicht()).thenReturn(tracks);
        when(mockedAfspeellijst.openTracksAfspeellijst(ID, true)).thenReturn(tracks);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);
        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.trackVoorAfspeellijst( ID, TOKEN));
    }
    @Test
    void testTrackVanAfspeellijstRoeptOpenTracksAfspeellijstAan() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        // Act
        Response result = afspeellijstControllerUnderTest.trackVanAfspeellijst(ID, TOKEN);
        // Assert
        verify(mockedAfspeellijst).openTracksAfspeellijst(ID,false);
    }
    @Test
    void testTrackVanAfspeellijstThrowsVerkeerdeTokenExceptie() {
        // Arrange
        List<Track> tracks = new ArrayList<Track>();
        when(mockedAfspeellijst.openTracksAfspeellijst(ID, false)).thenReturn(tracks);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);
        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.trackVanAfspeellijst( ID, TOKEN));
    }
    @Test
    void testVerwijderTrackVanAfspeellijstRoeptOpenTracksAfspeellijstAan() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        // Act
        Response result = afspeellijstControllerUnderTest.verwijderTrackVanAfspeellijst(ID,ID,TOKEN);
        // Assert
        verify(mockedAfspeellijst).verwijderTrack(ID,ID);
    }
    @Test
    void testVerwijderTrackVanAfspeellijstThrowsVerkeerdeTokenExceptie() {
        // Arrange
        List<Track> tracks = new ArrayList<Track>();
        when(mockedAfspeellijst.openTracksAfspeellijst(ID, false)).thenReturn(tracks);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);
        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.verwijderTrackVanAfspeellijst(ID,ID,TOKEN));
    }
    @Test
    void testTrackAanPlaylistToevoegenRoeptVoegTrackToeAan() {
        // Arrange
        when(mockedEigenaar.getEigenaar(TOKEN)).thenReturn(null);
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(0);
        trackDTO.setTitle("title");
        trackDTO.setPerformer("performer");
        trackDTO.setDuration(0);
        trackDTO.setAlbum("album");
        trackDTO.setPlaycount(0);
        trackDTO.setPublicationDate("publicationDate");
        trackDTO.setDescription("description");
        trackDTO.setOfflineAvailable(false);
        Afspeellijst afspeellijst = new Afspeellijst();
when(mockedSpotitube.openAfspeellijst(ID)).thenReturn(afspeellijst);
        // Act
        Response result = afspeellijstControllerUnderTest.trackAanPlaylistToevoegen(trackDTO, ID, "TOKEN");

        // Assert
        verify(mockedAfspeellijst).voegTrackToe(mockedTrackDTODataMapper.mapToDomain(trackDTO), afspeellijst);
    }
    @Test
    void testTrackAanPlaylistToevoegenThrowsVerkeerdeTokenExceptie() {
        // Arrange
        TrackDTO trackDTO = new TrackDTO();
        var afspeellijstenDTO = new AfspeellijstenDTO();
        var track = new Lied(ID, "a", 2,true, "a", "a");
        Afspeellijst afspeellijst = new Afspeellijst();
        when(mockedSpotitube.openAfspeellijst(ID)).thenReturn(afspeellijst);
        when(mockedTrackDTODataMapper.mapToDomain(trackDTO)).thenReturn(track);
        doNothing().when(mockedAfspeellijst).voegTrackToe(track, afspeellijst);
        when(mockedAfspeellijstenDTODataMapper.mapToDTO(mockedSpotitube.openOverzicht())).thenReturn(afspeellijstenDTO);
        doThrow(VerkeerdeTokenException.class).when(mockedEigenaar).getEigenaar(TOKEN);

        // Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> afspeellijstControllerUnderTest.trackAanPlaylistToevoegen(trackDTO, ID, TOKEN));
    }
}
