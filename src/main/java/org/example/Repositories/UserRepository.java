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

    public User loginUser(String email, String password) {
        User user = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"User\" WHERE \"Email\" = ? AND \"Password\" = ?")) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("UserID"), rs.getString("Name"), rs.getString("Email"), rs.getString("Password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: User login failed");
        }
        return user;
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

    public int getUserIdByEmail(String email) {
        int userId = -1;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT \"UserID\" FROM \"User\" WHERE \"Email\" = ?")) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}