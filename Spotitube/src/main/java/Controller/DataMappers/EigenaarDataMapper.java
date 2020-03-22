package Controller.DataMappers;

import Controller.DTOs.EigenaarDTO;
import Controller.DTOs.LoginDTO;
import Domain.Eigenaar;

public class EigenaarDataMapper {
    public LoginDTO mapToDTO(Eigenaar eigenaar){
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
