package controller.datamapper;

import controller.dtos.EigenaarDTO;
import controller.dtos.LoginDTO;
import domain.Eigenaar;

import javax.inject.Inject;

public class EigenaarDataMapper {
    private Eigenaar eigenaar;

@Inject
    public void setEigenaar(Eigenaar eigenaar) {
        this.eigenaar = eigenaar;
    }

    public LoginDTO mapToDTO(Eigenaar eigenaar) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(eigenaar.getGebruikersnaam());
        loginDTO.setToken(eigenaar.getToken());
        return loginDTO;
    }

    public Eigenaar mapToDomain(EigenaarDTO eigenaarDTO) {
        eigenaar.setGebruikersnaam(eigenaarDTO.getUser());
        eigenaar.setWachtwoord(eigenaarDTO.getPassword());
        return eigenaar;
    }
}

