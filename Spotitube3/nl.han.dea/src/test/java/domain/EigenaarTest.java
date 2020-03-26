package domain;

import datasource.daos.AfspeellijstDAO;
import datasource.daos.EigenaarDAO;
import domain.datamappers.EigenaarDataMapper;
import exceptions.eigenexcepties.OnjuistWachtwoordExceptie;
import exceptions.eigenexcepties.VerkeerdeTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EigenaarTest {
    private static final String TOKEN = "1234";
    private Eigenaar eigenaarUnderTest;
    private EigenaarDAO mockedEigenaarDAO;
    private AfspeellijstDAO mockedAfspeellijstDAO;
    private EigenaarDataMapper mockedEigenaarDataMapper;
    @BeforeEach
    void setUp() {
        eigenaarUnderTest = new Eigenaar();
        this.mockedEigenaarDAO = mock(EigenaarDAO.class);
        this.mockedAfspeellijstDAO = mock(AfspeellijstDAO.class);
        this.eigenaarUnderTest.setEigenaarDAO(mockedEigenaarDAO);
        this.eigenaarUnderTest.setAfspeellijstDao(mockedAfspeellijstDAO);
        this.mockedEigenaarDataMapper = mock(EigenaarDataMapper.class);
        this.eigenaarUnderTest.setEigenaarDataMapper(mockedEigenaarDataMapper);
    }

    @Test
    void testSetIngelogdRoeptSelectAan() throws Exception {
        // Arrange
        var inloggende = new Eigenaar();
        inloggende.setGebruikersnaam("gebruiker0");
        inloggende.setWachtwoord("wachtwoord");
        var eigenaar = new Eigenaar ();
        eigenaar.setGebruikersnaam("gebruiker0");
        eigenaar.setWachtwoord("dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");
        when(mockedEigenaarDataMapper.mapResultSetToDomain(mockedEigenaarDAO.select(inloggende.getGebruikersnaam()))).thenReturn(eigenaar);
        // Act
        eigenaarUnderTest.setIngelogd(inloggende);

        // Assert
        verify(mockedEigenaarDAO, times(2)).select(inloggende.getGebruikersnaam());
    }
    @Test
    void testSetIngelogdThrowtNiet() throws Exception {
        // Arrange
         var inloggende = new Eigenaar();
        inloggende.setGebruikersnaam("gebruiker0");
        inloggende.setWachtwoord("wachtwoord");
         var eigenaar = new Eigenaar ();
         eigenaar.setGebruikersnaam("gebruiker0");
         eigenaar.setWachtwoord("dc00c903852bb19eb250aeba05e534a6d211629d77d055033806b783bae09937");
        when(mockedEigenaarDataMapper.mapResultSetToDomain(mockedEigenaarDAO.select(inloggende.getGebruikersnaam()))).thenReturn(eigenaar);
        doNothing().when(mockedEigenaarDAO).update(eigenaar);
        // Act
        eigenaarUnderTest.setIngelogd(inloggende);

        // Assert

    }

    @Test
    void testSetIngelogd_ThrowsOnjuistWachtwoordExceptie() throws OnjuistWachtwoordExceptie {
        // Arrange
        var inloggende = new Eigenaar();
        inloggende.setGebruikersnaam("gebruiker0");
        inloggende.setWachtwoord("wachtwoord");
        var eigenaar = new Eigenaar ();
        eigenaar.setGebruikersnaam("gebruiker0");
        eigenaar.setWachtwoord("foutiefWachtwoord");
        when(mockedEigenaarDataMapper.mapResultSetToDomain(mockedEigenaarDAO.select(inloggende.getGebruikersnaam()))).thenReturn(eigenaar);
        doNothing().when(mockedEigenaarDAO).update(eigenaar);
        //Act&Assert
        assertThrows(OnjuistWachtwoordExceptie.class, () -> eigenaarUnderTest.setIngelogd(inloggende));
    }
    @Test
    void testSetIngelogd_ThrowsNotFoundException() throws OnjuistWachtwoordExceptie {
        // Arrange
        var inloggende = new Eigenaar();
        inloggende.setGebruikersnaam("gebruiker0");
        inloggende.setWachtwoord("wachtwoord");
        when(mockedEigenaarDAO.select(inloggende.getGebruikersnaam())).thenReturn(null);
        doNothing().when(mockedEigenaarDAO).update(null);
        //Act&Assert
        assertThrows(NotFoundException.class, () -> eigenaarUnderTest.setIngelogd(inloggende));
    }
    @Test
    void testMaakAfspeellijstRoeptBerekentMaxIdAan() {
        // Arrange
         Afspeellijst afspeellijst = new Afspeellijst();
        doNothing().when(mockedAfspeellijstDAO).insert(afspeellijst);

        // Act
        eigenaarUnderTest.maakAfspeellijst(afspeellijst);

        // Assert
        verify(mockedAfspeellijstDAO).getMaxId();
    }
    @Test
    void testMaakAfspeellijstRoeptInsertAan() {
        // Arrange
        Afspeellijst afspeellijst = new Afspeellijst();
        when(mockedAfspeellijstDAO.getMaxId()).thenReturn(10);

        // Act
        eigenaarUnderTest.maakAfspeellijst(afspeellijst);
        // Assert
        verify(mockedAfspeellijstDAO).insert(afspeellijst);
    }

    @Test
    void testWijzigAfspeellijst() {
        // Arrange
         Afspeellijst afspeellijst = new Afspeellijst();
        // Act
        eigenaarUnderTest.wijzigAfspeellijst(afspeellijst);
        // Assert
        verify(mockedAfspeellijstDAO).update(afspeellijst);
    }
    @Test
    void testGetEigenaarReturnJuisteEigenaar() {
        // Arrange
        var token = "1234";
        var eigenaar = new Eigenaar();
        eigenaar.setGebruikersnaam("gebruiker0");
        when(mockedEigenaarDataMapper.mapResultSetToDomain(mockedEigenaarDAO.getEigenaarMetToken(TOKEN))).thenReturn(eigenaar);
        // Act
        Eigenaar actual = eigenaarUnderTest.getEigenaar(token);

        // Assert
        assertEquals(actual, eigenaar);
    }

    @Test
    void testGetEigenaar_ThrowsVerkeerdeTokenException() {
        // Arrange
        var token = "1234";
        when(mockedEigenaarDataMapper.mapResultSetToDomain(mockedEigenaarDAO.getEigenaarMetToken(TOKEN))).thenReturn(null);
        //Act&Assert
        assertThrows(VerkeerdeTokenException.class, () -> eigenaarUnderTest.getEigenaar(token));
    }
}
