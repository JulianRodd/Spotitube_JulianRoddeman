package controller.datamapper;

import controller.dtos.EigenaarDTO;
import controller.dtos.LoginDTO;
import controller.dtos.TrackDTO;
import domain.Eigenaar;
import domain.Lied;
import domain.Track;
import domain.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EigenaarDataMapperTest {

    private EigenaarDataMapper eigenaarDataMapperUnderTest;

    @BeforeEach
    void setUp() {
        eigenaarDataMapperUnderTest = new EigenaarDataMapper();
    }

    @Test
    void testMapToDomain() {
        // Arrange
        var eigenaarDTO = new EigenaarDTO();
        eigenaarDTO.setUser("user");
        eigenaarDTO.setPassword("password");

        // Act
        var actual = eigenaarDataMapperUnderTest.mapToDomain(eigenaarDTO);

        // Assert
        assertEquals(actual.getGebruikersnaam(), eigenaarDTO.getUser());
        assertEquals(actual.getWachtwoord(),eigenaarDTO.getPassword());
    }

    @Test
    void testMapToDTO() {
        // Arrange
        var eigenaar = new Eigenaar();
        eigenaar.setGebruikersnaam("user");
        eigenaar.setWachtwoord("password");
        eigenaar.setToken("1234");
        // Act
        var actual = eigenaarDataMapperUnderTest.mapToDTO(eigenaar);

        // Assert
        assertEquals(eigenaar.getGebruikersnaam(), actual.getUser());
        assertEquals(eigenaar.getToken(), actual.getToken());
    }

}
