package Datasource.DAOs;

import Datasource.util.DatabaseProperties;
import Domain.Afspeellijst;
import Domain.Lied;
import Domain.Video;
import Domain.Track;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AfspeellijstDao {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    public AfspeellijstDao() {
        databaseProperties = new DatabaseProperties();
    }


    public String select(String pk, String kolom) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT "+kolom+" FROM afspeellijst WHERE id = ?");
            statement.setString(1,pk);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString(kolom);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Afspeellijst afspeellijst) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE afspeellijst SET naam = ?, eigenaar = ? WHERE id = ?");
            statement.setString(1,afspeellijst.getNaam());
            statement.setString(2,afspeellijst.getEigenaar());
            statement.setInt(3,afspeellijst.getId());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Afspeellijst> selectAll() {
        List<Afspeellijst> afspeellijsten = new ArrayList<Afspeellijst>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM afspeellijst ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                    afspeellijsten.add(new Afspeellijst(resultSet.getInt("id"), resultSet.getString("naam"), resultSet.getString("eigenaar")));
            }
            return afspeellijsten;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Afspeellijst selectAfspeellijst(int id) {
        Afspeellijst afspeellijst;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM afspeellijst WHERE id = ?");
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                    afspeellijst = new Afspeellijst(resultSet.getInt("id"), resultSet.getString("naam"), resultSet.getString("eigenaar"));
                return afspeellijst;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(int id) {
        Afspeellijst afspeellijst;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("DELETE FROM afspeellijst WHERE id = ?");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int insert(Afspeellijst afspeellijst) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO afspeellijst VALUES (?,?,?)");
            statement.setInt(1,afspeellijst.getId());
            statement.setString(2,afspeellijst.getNaam());
            statement.setString(3,afspeellijst.getEigenaar());
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
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
