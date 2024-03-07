package dao;

import db.Database;
import model.Client;
import org.example.MigrationMain;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private Connection connection = Database.getInstance().getConnection();
    public long create(String name) throws SQLException {
        validateName(name);
        long nextId = getNextAvailableId();
        String sql = "INSERT INTO CLIENT (id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, nextId);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            return nextId;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating client with name " + name, e);
        }
    }

    private long getNextAvailableId() throws SQLException {
        String sql = "SELECT MAX(id) FROM CLIENT";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getLong(1) + 1;
            } else {
                return 1;
            }
        }
    }
    public void validateName(String name){
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name can't be empty ");
        }
        else if (name.length()<2||name.length()>1000) {
            throw new IllegalArgumentException("Length of name must be bigger than 2 and less than 1000");

        }

    }
    public String geById(long id){
        String sql = "SELECT name FROM CLIENT WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;

    }
    public void setName(long id,String name){
        String sql = "UPDATE CLIENT SET name = ? WHERE id = ? ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(long id){
        String sql = "DELETE FROM CLIENT WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Client> listAll() throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM CLIENT";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();){

            while (resultSet.next()){
                Client client = new Client();
                client.setName(resultSet.getString("name"));
                client.setId(resultSet.getLong("id"));
                clients.add(client);


            }
        }
        return clients;
    }

}
