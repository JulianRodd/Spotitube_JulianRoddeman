package controller.datamapper;

import controller.dtos.EigenaarDTO;
import controller.dtos.LoginDTO;
import domain.Eigenaar;

public class EigenaarDTODataMapper {

    public LoginDTO mapToDTO(Eigenaar eigenaar) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser(eigenaar.getGebruikersnaam());
        loginDTO.setToken(eigenaar.getToken());
        return loginDTO;
    }

    public Eigenaar mapToDomain(EigenaarDTO eigenaarDTO) {
        Eigenaar eigenaar = new Eigenaar();
        eigenaar.setGebruikersnaam(eigenaarDTO.getUser());
        eigenaar.setWachtwoord(eigenaarDTO.getPassword());
        return eigenaar;
    }
}

