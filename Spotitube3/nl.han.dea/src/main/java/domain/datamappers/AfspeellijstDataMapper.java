package domain.datamappers;

import domain.Afspeellijst;
import exceptions.eigenexcepties.DatabaseFoutException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AfspeellijstDataMapper {
    public Afspeellijst mapResultSetToDomain(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Afspeellijst afspeellijst = new Afspeellijst();
                    afspeellijst.setId(resultSet.getInt("id"));
                    afspeellijst.setNaam(resultSet.getString("naam"));
                    afspeellijst.setEigenaar(resultSet.getString("eigenaar"));
                    return afspeellijst;
                }
            } catch (SQLException e) {
                throw new DatabaseFoutException("Er is heeft een fout opgetreden bij het omzetten van resultset naar Afspeellijst");
            }
        }
        return null;
    }

    public List<Afspeellijst> mapResultSetToListDomain(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                List<Afspeellijst> afspeellijsten = new ArrayList<Afspeellijst>();
                while (resultSet.next()) {
                    Afspeellijst afspeellijst = new Afspeellijst();
                    afspeellijst.setId(resultSet.getInt("id"));
                    afspeellijst.setNaam(resultSet.getString("naam"));
                    afspeellijst.setEigenaar(resultSet.getString("eigenaar"));
                    afspeellijsten.add(afspeellijst);
                }
                return afspeellijsten;
            } catch(SQLException e){
                throw new DatabaseFoutException("Er is heeft een fout opgetreden bij het omzetten van resultset naar Afspeellijsten");
            }
        }
        return null;
    }
}
