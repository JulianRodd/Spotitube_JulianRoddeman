package domain;

import datasource.daos.AfspeellijstDAO;
import datasource.daos.EigenaarDAOImpl;
import exceptions.eigenexcepties.OnjuistWachtwoordExceptie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EigenaarTest {

    private Eigenaar eigenaarUnderTest;
    private EigenaarDAOImpl mockedEigenaarDAO;
    private AfspeellijstDAO mockedAfspeellijstDAO;
    @BeforeEach
    void setUp() {
        eigenaarUnderTest = new Eigenaar();
        this.mockedEigenaarDAO = mock(EigenaarDAOImpl.class);
        this.mockedAfspeellijstDAO = mock(AfspeellijstDAO.class);
        this.eigenaarUnderTest.setEigenaarDAO(mockedEigenaarDAO);
        this.eigenaarUnderTest.setAfspeellijstDao(mockedAfspeellijstDAO);
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
        when(mockedEigenaarDAO.select(inloggende.getGebruikersnaam())).thenReturn(eigenaar);
        // Act
        eigenaarUnderTest.setIngelogd(inloggende);

        // Assert
        verify(mockedEigenaarDAO).select(inloggende.getGebruikersnaam());

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
         when(mockedEigenaarDAO.select(inloggende.getGebruikersnaam())).thenReturn(eigenaar);
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
        when(mockedEigenaarDAO.select(inloggende.getGebruikersnaam())).thenReturn(eigenaar);
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
}
