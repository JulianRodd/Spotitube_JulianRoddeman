package domain;

import datasource.daos.AfspeellijstDAO;
import datasource.daos.EigenaarDAO;
import datasource.daos.TrackDAO;
import domain.datamappers.AfspeellijstDataMapper;
import domain.datamappers.TrackDataMapper;
import exceptions.eigenexcepties.VerkeerdeTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SpotitubeTest {
    private static final int ID = 1;
    private Spotitube spotitubeUnderTest;
    private AfspeellijstDAO mockedAfspeellijstDAO;
    private TrackDAO mockedTrackDAO;
    private Afspeellijst mockedAfspeellijst;
    private AfspeellijstDataMapper mockedAfspeellijstDataMapper;
    private TrackDataMapper mockedTrackDataMapper;
    @BeforeEach
    void setUp() {
        spotitubeUnderTest = new Spotitube();
        this.mockedTrackDAO = mock(TrackDAO.class);
        this.mockedAfspeellijstDAO = mock(AfspeellijstDAO.class);
        this.mockedAfspeellijst = mock(Afspeellijst.class);
        this.mockedAfspeellijstDataMapper = mock(AfspeellijstDataMapper.class);
        this.mockedTrackDataMapper = mock(TrackDataMapper.class);
        this.spotitubeUnderTest.setTrackDataMapper(mockedTrackDataMapper);
        this.spotitubeUnderTest.setAfspeellijstDataMapper(mockedAfspeellijstDataMapper);
        this.spotitubeUnderTest.setTrackDAO(mockedTrackDAO);
        this.spotitubeUnderTest.setAfspeellijstDAO(mockedAfspeellijstDAO);
        this.spotitubeUnderTest.setAfspeellijst(mockedAfspeellijst);
    }

    @Test
    void testOpenAfspeellijstReturndJuisteAfspeellijst() {
        // Arrange
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(ID);
        var tracks = new ArrayList<Track>();
        tracks.add(new Lied(ID, "a",  1, true, "a", "a"));
        tracks.add(new Lied(ID, "b",  1, true, "a", "a"));
        when(mockedAfspeellijstDataMapper.mapResultSetToDomain(mockedAfspeellijstDAO.select(ID))).thenReturn(afspeellijst);
        when(mockedAfspeellijst.openTracksAfspeellijst(ID,false)).thenReturn(tracks);
        // Act
        var actual = spotitubeUnderTest.openAfspeellijst(ID);
        var expected = afspeellijst;
        expected.setTracks(tracks);
        // Assert
        assertEquals(actual,expected);
    }
    @Test
    void testOpenAfspeellijstRoeptSelectAan() {
        // Arrange
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(ID);
        var tracks = new ArrayList<Track>();
        tracks.add(new Lied(ID, "a", 1, true, "a", "a"));
        tracks.add(new Lied(ID, "b",  1, true, "a", "a"));
        when(mockedAfspeellijstDataMapper.mapResultSetToDomain(mockedAfspeellijstDAO.select(ID))).thenReturn(afspeellijst);
        when(mockedAfspeellijst.openTracksAfspeellijst(ID,false)).thenReturn(tracks);
        // Act
        spotitubeUnderTest.openAfspeellijst(ID);
        // Assert
        verify(mockedAfspeellijstDAO, times(2)).select(ID);
    }
    @Test
    void testOpenAfspeellijstRoeptOpenTracksAan() {
        // Arrange
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(ID);
        var tracks = new ArrayList<Track>();
        tracks.add(new Lied(ID, "a",  1, true, "a", "a"));
        tracks.add(new Lied(ID, "b",  1, true, "a", "a"));
        when(mockedAfspeellijstDataMapper.mapResultSetToDomain(mockedAfspeellijstDAO.select(ID))).thenReturn(afspeellijst);
        when(mockedAfspeellijst.openTracksAfspeellijst(ID,false)).thenReturn(tracks);
        // Act
        spotitubeUnderTest.openAfspeellijst(ID);
        // Assert
        verify(mockedAfspeellijst).openTracksAfspeellijst(ID,false);
    }
    @Test
    void testOpenOverzichtReturndJuisteAfspeellijsten() {
        // Arrange
        var afspeellijsten = new ArrayList<Afspeellijst>();
        afspeellijsten.add(new Afspeellijst());
        afspeellijsten.add(new Afspeellijst());
        var tracks = new ArrayList<Track>();
        tracks.add(new Lied(ID, "b",  1, true, "a", "a"));
        afspeellijsten.get(0).setTracks(tracks);
        afspeellijsten.get(1).setTracks(tracks);
        when(mockedAfspeellijstDataMapper.mapResultSetToListDomain(mockedAfspeellijstDAO.selectAll())).thenReturn(afspeellijsten);
        when(mockedAfspeellijst.openTracksAfspeellijst(ID,false)).thenReturn(tracks);

        // Act
         List<Afspeellijst> actual = spotitubeUnderTest.openOverzicht();

        // Assert
        assertEquals(actual, afspeellijsten);

    }
    @Test
    void testOpenOverzichtRoeptSelectAllAan() {
        // Arrange
        var afspeellijsten = new ArrayList<Afspeellijst>();
        var tracks = new ArrayList<Track>();
        when(mockedAfspeellijstDataMapper.mapResultSetToListDomain(mockedAfspeellijstDAO.selectAll())).thenReturn(afspeellijsten);
        when(mockedAfspeellijst.openTracksAfspeellijst(ID,false)).thenReturn(tracks);

        // Act
        spotitubeUnderTest.openOverzicht();

        // Assert
        verify(mockedAfspeellijstDAO, times(2)).selectAll();

    }
    @Test
    void testOpenOverzichtRoeptOpenAfspeellijstAan() {
        // Arrange
        var afspeellijsten = new ArrayList<Afspeellijst>();
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(ID);
        afspeellijsten.add(afspeellijst);
        var tracks = new ArrayList<Track>();
        when(mockedAfspeellijstDataMapper.mapResultSetToListDomain(mockedAfspeellijstDAO.selectAll())).thenReturn(afspeellijsten);
        when(mockedAfspeellijst.openTracksAfspeellijst(ID,false)).thenReturn(tracks);

        // Act
        spotitubeUnderTest.openOverzicht();

        // Assert
        verify(mockedAfspeellijst).openTracksAfspeellijst(ID, false);

    }
    @Test
    void testToonTrackOverzichtRoeptSelectAan() {
        // Arrange

        // Act
        spotitubeUnderTest.toonTrackOverzicht();

        // Assert
        verify(mockedTrackDAO).selectAll();
    }
    @Test
    void testToonTrackOverzichtRoeptDataMapperAan() {
        // Arrange

        // Act
        spotitubeUnderTest.toonTrackOverzicht();

        // Assert
        verify(mockedTrackDataMapper).mapResultSetToListDomain(mockedTrackDAO.selectAll());
    }
    @Test
    void testVerwijderAfspeellijst() {
        // Arrange

        // Act
        spotitubeUnderTest.verwijderAfspeellijst(ID);

        // Assert
        verify(mockedAfspeellijstDAO).delete(ID);
    }

}
