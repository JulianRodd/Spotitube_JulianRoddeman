package domain.datamappers;

import com.mysql.cj.protocol.Resultset;
import domain.Eigenaar;

import java.sql.*;

public class EigenaarDataMapper {

    public Eigenaar mapResultSetToDomain(ResultSet resultSet) {
        if (resultSet != null) {
        try {
                while (resultSet.next()) {
                    Eigenaar eigenaar = new Eigenaar();
                    eigenaar.setGebruikersnaam(resultSet.getString("gebruikersnaam"));
                    eigenaar.setWachtwoord(resultSet.getString("wachtwoord"));
                    return eigenaar;
                }
            } catch(SQLException e){

            }
        }
        return null;
    }
}
