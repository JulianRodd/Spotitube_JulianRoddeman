package controller.datamapper;

import controller.dtos.EigenaarDTO;
import domain.Eigenaar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EigenaarDTODataMapperTest {

    private EigenaarDTODataMapper eigenaarDTODataMapperUnderTest;

    @BeforeEach
    void setUp() {
        eigenaarDTODataMapperUnderTest = new EigenaarDTODataMapper();
    }

    @Test
    void testMapToDomain() {
        // Arrange
        var eigenaarDTO = new EigenaarDTO();
        eigenaarDTO.setUser("user");
        eigenaarDTO.setPassword("password");

        // Act
        var actual = eigenaarDTODataMapperUnderTest.mapToDomain(eigenaarDTO);

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
        var actual = eigenaarDTODataMapperUnderTest.mapToDTO(eigenaar);

        // Assert
        assertEquals(eigenaar.getGebruikersnaam(), actual.getUser());
        assertEquals(eigenaar.getToken(), actual.getToken());
    }

}
