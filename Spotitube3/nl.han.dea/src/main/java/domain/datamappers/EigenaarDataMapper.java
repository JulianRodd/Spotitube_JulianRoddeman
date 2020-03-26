package domain.datamappers;
import domain.Eigenaar;
import exceptions.eigenexcepties.DatabaseFoutException;
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
            throw new DatabaseFoutException("Er is heeft een fout opgetreden bij het omzetten van resultset naar Eigenaar");
        }
        }
        return null;
    }
}
