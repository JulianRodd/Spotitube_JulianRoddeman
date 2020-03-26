package domain.datamappers;

import datasource.connectie.DatabaseProperties;
import domain.Afspeellijst;
import exceptions.eigenexcepties.DatabaseFoutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AfspeellijstDataMapperTest {

    private AfspeellijstDataMapper afspeellijstDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        afspeellijstDataMapperUnderTest = new AfspeellijstDataMapper();
    }

    @Test
    void testMapResultSetToDomain() throws SQLException {
        // Arrange
        var databaseProperties = new DatabaseProperties();
        var connection = DriverManager.getConnection(databaseProperties.connectionString());
        var statement = connection.prepareStatement("SELECT * FROM afspeellijst WHERE id = ?");
        statement.setInt(1, 4);
        var resultSet = statement.executeQuery();
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(4);
        afspeellijst.setNaam("Coole lijstjeee3");
        // Act
        Afspeellijst actual = afspeellijstDataMapperUnderTest.mapResultSetToDomain(resultSet);

        // Assert
        assertEquals(afspeellijst.getId(), actual.getId());
        assertEquals(afspeellijst.getNaam(), actual.getNaam());
        assertEquals(afspeellijst.getEigenaar(), actual.getEigenaar());
    }
    @Test
    void testMapResultSetToDomainReturnsNull() {
        // Arrange
        ResultSet resultSet = null;

        // Act
        final Afspeellijst actual = afspeellijstDataMapperUnderTest.mapResultSetToDomain(resultSet);

        // Assert
        assertEquals(null, actual);
    }
    @Test
    void testMapResultSetToDomainThrowsSQLException() throws SQLException {
        // Arrange
        var databaseProperties = new DatabaseProperties();
        var connection = DriverManager.getConnection(databaseProperties.connectionString());
        var statement = connection.prepareStatement("SELECT naam FROM afspeellijst WHERE id = ?");
        statement.setInt(1, 4);
        var resultSet = statement.executeQuery();
        // Act & Assert
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDataMapperUnderTest.mapResultSetToDomain(resultSet));
    }
    @Test
    void testMapResultSetToListDomain() throws SQLException {
        // Arrange
        var databaseProperties = new DatabaseProperties();
        var connection = DriverManager.getConnection(databaseProperties.connectionString());
        var statement = connection.prepareStatement("SELECT * FROM afspeellijst WHERE id = ?");
        statement.setInt(1, 4);
        var resultSet = statement.executeQuery();
        var afspeellijst = new Afspeellijst();
        afspeellijst.setId(4);
        afspeellijst.setNaam("Coole lijstjeee3");
        // Act
        var actual = afspeellijstDataMapperUnderTest.mapResultSetToListDomain(resultSet);

        // Assert
        assertEquals(afspeellijst.getId(), actual.get(0).getId());
        assertEquals(afspeellijst.getNaam(), actual.get(0).getNaam());
        assertEquals(afspeellijst.getEigenaar(), actual.get(0).getEigenaar());
    }
    @Test
    void testMapResultSetToListDomainReturnsNull() {
        // Arrange
        ResultSet resultSet = null;

        // Act
        final List<Afspeellijst> actual = afspeellijstDataMapperUnderTest.mapResultSetToListDomain(resultSet);

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
        assertThrows(DatabaseFoutException.class, () -> afspeellijstDataMapperUnderTest.mapResultSetToListDomain(resultSet));
    }
}
