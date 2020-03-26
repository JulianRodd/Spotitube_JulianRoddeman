package domain.datamappers;

import datasource.connectie.DatabaseProperties;
import domain.Afspeellijst;
import domain.Lied;
import domain.Track;
import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TrackDataMapperTest {

    private TrackDataMapper trackDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        trackDataMapperUnderTest = new TrackDataMapper();
    }

    @Test
    void testMapResultSetToDomain() throws SQLException {
        // Arrange
        var databaseProperties = new DatabaseProperties();
        var connection = DriverManager.getConnection(databaseProperties.connectionString());
        var statement = connection.prepareStatement("SELECT * " +
                "FROM track LEFT JOIN lied " +
                "ON track.id = lied.id " +
                "LEFT JOIN video " +
                "ON track.id=video.id "+
                "AND track.id = ?");
        statement.setInt(1, 1);
        var resultSet = statement.executeQuery();
        var track = new Lied(1, "Eugenia",116,false, "Connor", "Naida Kerr");
        // Act
        var actual = trackDataMapperUnderTest.mapResultSetToListDomain(resultSet);

        // Assert
        assertEquals(track.getId(), actual.get(0).getId());
        assertEquals(track.getTitel(), actual.get(0).getTitel());
        assertEquals(track.getAfspeelduur(), actual.get(0).getAfspeelduur());
        assertEquals(track.isOfflineBeschikbaar(), actual.get(0).isOfflineBeschikbaar());
        assertEquals(track.getPerformer(), actual.get(0).getPerformer());
        assertEquals(track.getAlbum(), ((Lied)actual.get(0)).getAlbum());
    }
    @Test
    void testMapResultSetToListDomainReturnsNull() {
        // Arrange
        ResultSet resultSet = null;

        // Act
        final List<Track> actual = trackDataMapperUnderTest.mapResultSetToListDomain(resultSet);

        // Assert
        assertEquals(null, actual);
    }
    @Test
    void testMapResultSetToListDomainThrowsSQLException() throws SQLException {
        // Arrange
        var databaseProperties = new DatabaseProperties();
        var connection = DriverManager.getConnection(databaseProperties.connectionString());
        var statement = connection.prepareStatement("SELECT naam FROM afspeellijst WHERE id = ?");
        statement.setInt(1, 4);
        var resultSet = statement.executeQuery();
        // Act & Assert
        assertThrows(DatabaseFoutException.class, () -> trackDataMapperUnderTest.mapResultSetToListDomain(resultSet));
    }
}

