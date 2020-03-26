package controller;

import controller.datamapper.EigenaarDTODataMapper;
import controller.dtos.EigenaarDTO;
import domain.Eigenaar;
import exceptions.eigenexcepties.OnjuistWachtwoordExceptie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EigenaarControllerTest {

    private EigenaarController eigenaarControllerUnderTest;
    private Eigenaar mockedEigenaar;
    private EigenaarDTODataMapper mockedEigenaarDTODataMapper;

    @BeforeEach
    void setUp() {
        eigenaarControllerUnderTest = new EigenaarController();
        this.mockedEigenaar = mock(Eigenaar.class);
        this.mockedEigenaarDTODataMapper = mock(EigenaarDTODataMapper.class);
        this.eigenaarControllerUnderTest.setEigenaar(mockedEigenaar);
        this.eigenaarControllerUnderTest.setEigenaarDTODataMapper(mockedEigenaarDTODataMapper);
    }

    @Test
    void testLoginReturnsHttp401BijFoutWachtwoord() throws OnjuistWachtwoordExceptie {
        // Arrange
        EigenaarDTO eigenaarDTO = new EigenaarDTO();
        Eigenaar eigenaar = new Eigenaar();
        eigenaar.setGebruikersnaam("gebruiker0");
        eigenaar.setWachtwoord("foutWachtwoord");
        when(mockedEigenaarDTODataMapper.mapToDomain(eigenaarDTO)).thenReturn(eigenaar);
        doThrow(OnjuistWachtwoordExceptie.class).when(mockedEigenaar).setIngelogd(eigenaar);
        // Act
         Response result = eigenaarControllerUnderTest.login(eigenaarDTO);
        // Assert
        assertEquals(401, result.getStatus());
    }
    @Test
    void testLoginReturnsNotFoundExceptionBijNietGevondenEigenaar() throws OnjuistWachtwoordExceptie {
        // Arrange
        EigenaarDTO eigenaarDTO = new EigenaarDTO();
        Eigenaar eigenaar = new Eigenaar();
        eigenaar.setGebruikersnaam("gebruiker99999");
        eigenaar.setWachtwoord("wachtwoord");
        when(mockedEigenaarDTODataMapper.mapToDomain(eigenaarDTO)).thenReturn(eigenaar);
        doThrow(NotFoundException.class).when(mockedEigenaar).setIngelogd(eigenaar);
        // Act&Assert
        assertThrows(NotFoundException.class, () -> eigenaarControllerUnderTest.login(eigenaarDTO));
    }
    @Test
    void testLoginGebruiktSetIngelogd() {
        // Arrange
        EigenaarDTO eigenaarDTO = new EigenaarDTO();
        eigenaarDTO.setUser("gebruiker0");
        eigenaarDTO.setPassword("wachtwoord");
        Eigenaar eigenaar = new Eigenaar();
        eigenaar.setGebruikersnaam("gebruiker0");
        eigenaar.setGebruikersnaam("wachtwoord");
        when(mockedEigenaarDTODataMapper.mapToDomain(eigenaarDTO)).thenReturn(eigenaar);
        // Act
        eigenaarControllerUnderTest.login(eigenaarDTO);
        // Assert
        try {
            verify(mockedEigenaar).setIngelogd(eigenaar);
        } catch (OnjuistWachtwoordExceptie onjuistWachtwoordExceptie) {
            onjuistWachtwoordExceptie.printStackTrace();
        }
    }

    @Test
    void testLoginGebruiktMapToDomain() {
        // Arrange
        EigenaarDTO eigenaarDTO = new EigenaarDTO();
        eigenaarDTO.setUser("gebruiker0");
        eigenaarDTO.setPassword("wachtwoord");
        // Act
        eigenaarControllerUnderTest.login(eigenaarDTO);
        // Assert
            verify(mockedEigenaarDTODataMapper).mapToDomain(eigenaarDTO);
    }


}
