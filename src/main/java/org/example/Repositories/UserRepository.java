package org.example.Repositories;
import org.example.Connections.DBConnection;
import org.example.Models.User;

import java.sql.*;

public class UserRepository {
    public void addUser(String name, String email, String password) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO \"User\" (\"Name\", \"Email\", \"Password\") VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"User\" WHERE \"UserID\" = ?")) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("UserID"), rs.getString("Name"), rs.getString("Email"), rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
