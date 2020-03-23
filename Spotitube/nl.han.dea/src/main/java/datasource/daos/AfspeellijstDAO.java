package datasource.daos;

import datasource.util.DatabaseProperties;
import domain.Afspeellijst;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AfspeellijstDAO{
    private Logger logger = Logger.getLogger(getClass().getName());
    private DatabaseProperties databaseProperties;

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public void update(Object object) {
        try {
            Afspeellijst afspeellijst = (Afspeellijst) object;
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE afspeellijst SET naam = ?, eigenaar = ? WHERE id = ?");
            statement.setString(1, afspeellijst.getNaam());
            statement.setString(2, afspeellijst.getEigenaar());
            statement.setInt(3, afspeellijst.getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Object> selectAll() {
        List<Object> afspeellijsten = new ArrayList<Object>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM afspeellijst ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Afspeellijst afspeellijst = new Afspeellijst();
                afspeellijst.setId(resultSet.getInt("id"));
                afspeellijst.setNaam(resultSet.getString("naam"));
                afspeellijst.setEigenaar(resultSet.getString("eigenaar"));
                afspeellijsten.add(afspeellijst);
            }
            return afspeellijsten;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object select(int pk) {
        Afspeellijst afspeellijst;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM afspeellijst WHERE id = ?");
            statement.setInt(1, pk);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                afspeellijst = new Afspeellijst();
                afspeellijst.setId(resultSet.getInt("id"));
                afspeellijst.setNaam(resultSet.getString("naam"));
                afspeellijst.setEigenaar(resultSet.getString("eigenaar"));
                return afspeellijst;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(int pk) {
        Afspeellijst afspeellijst;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("DELETE FROM afspeellijst WHERE id = ?");
            statement.setInt(1, pk);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insert(Object object) {
        try {
            Afspeellijst afspeellijst = (Afspeellijst) object;
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO afspeellijst VALUES (?,?,?)");
            statement.setInt(1, afspeellijst.getId());
            statement.setString(2, afspeellijst.getNaam());
            statement.setString(3, afspeellijst.getEigenaar());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getMaxId() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) as 'a' FROM afspeellijst");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
