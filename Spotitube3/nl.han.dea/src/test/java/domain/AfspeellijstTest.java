package domain;

import datasource.daos.AfspeellijstTrackDAO;
import datasource.daos.TrackDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AfspeellijstTest {
    private static final int ID = 1;
    private Afspeellijst afspeellijstUnderTest;
    private TrackDAO mockedTrackDAO;
    private AfspeellijstTrackDAO mockedAfspeellijstTrackDAO;

    @BeforeEach
    void setUp() {
        afspeellijstUnderTest = new Afspeellijst();
        this.mockedTrackDAO = mock(TrackDAO.class);
        this.mockedAfspeellijstTrackDAO = mock(AfspeellijstTrackDAO.class);
        this.afspeellijstUnderTest.setTrackDAO(mockedTrackDAO);
        this.afspeellijstUnderTest.setAfspeellijstTrackDAO(mockedAfspeellijstTrackDAO);
    }

    @Test
    void testBerekenAfspeellijstRoeptSelectAan() {
        // Arrange
        var tracks = new ArrayList<Track>();
        var afspeelduur = 10;
        tracks.add(new Lied(ID, "a", null, afspeelduur, true, "a", "a"));
        tracks.add(new Lied(ID, "b", null, afspeelduur, true, "a", "a"));
        // Act
        int actual = afspeellijstUnderTest.berekenAfspeellijstLengte(ID);

        // Assert
        verify(mockedAfspeellijstTrackDAO).select(ID, false);
    }

    @Test
    void testBerekenAfspeellijstLengteOfLengteKlopt() {
        // Arrange
        var tracks = new ArrayList<Track>();
        var afspeelduur = 10;
        tracks.add(new Lied(ID, "a", null, afspeelduur, true, "a", "a"));
        tracks.add(new Lied(ID, "b", null, afspeelduur, true, "a", "a"));
        when(mockedAfspeellijstTrackDAO.select(ID, false)).thenReturn(tracks);
        // Act
        int actual = afspeellijstUnderTest.berekenAfspeellijstLengte(ID);

        // Assert
        assertEquals(afspeelduur * 2, actual);
    }


    @Test
    void testVoegTracksToeCallsTrackDAOInsert() {
        // Arrange
        var track = new Lied(ID, "a", null, 1, true, "a", "a");
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(ID);
        doNothing().when(mockedAfspeellijstTrackDAO).insert(afspeellijst.getId(), track.getId());
        // Act
        afspeellijstUnderTest.voegTrackToe(track, afspeellijst);

        // Assert
        verify(mockedTrackDAO).insert(track);
    }
    @Test
    void testVoegTracksToeCallsAfspeellijstTrackDAOIInsert() {
        // Arrange
        var track = new Lied(ID, "a", null, 1, true, "a", "a");
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(ID);
        doNothing().when(mockedTrackDAO).insert(track);
        // Act
        afspeellijstUnderTest.voegTrackToe(track, afspeellijst);

        // Assert
        verify(mockedAfspeellijstTrackDAO).insert(afspeellijst.getId(), track.getId());
    }


    @Test
    void testVerwijderTrackRoeptDeleteAan() {
        // Arrange

        // Act
        afspeellijstUnderTest.verwijderTrack(ID, ID);

        // Assert
        verify(mockedAfspeellijstTrackDAO).deletePlaylistFromTrack(ID,ID);
    }

    @Test
    void testOpenTracksVoorAfspeellijstRoeptSelectAanBijFalse() {
        // Arrange

        // Act
        afspeellijstUnderTest.openTracksAfspeellijst(ID, false);

        // Assert
        verify(mockedAfspeellijstTrackDAO).select(ID, false);
    }

    @Test
    void testOpenTracksVoorAfspeellijstRoeptSelectAanBijTrue() {
        // Arrange

        // Act
        afspeellijstUnderTest.openTracksAfspeellijst(ID, true);

        // Assert
        verify(mockedAfspeellijstTrackDAO).select(ID, true);
    }
}
