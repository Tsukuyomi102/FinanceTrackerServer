package org.example.Repositories;

import org.example.Connections.DBConnection;
import org.example.Models.Card;
import org.example.Models.Cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CashRepository {

    public void addCash(int userid, int balance, String name, String description){
        try{
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO \"Cash\"(\"UserID\", \"Balance\", \"Name\", \"Description\") VALUES (?, ?, ?, ?)";
            assert  connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userid);
            statement.setInt(2, balance);
            statement.setString(3, name);
            statement.setString(4, description);
            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Cash> getCashByUserId(int userid){
        List<Cash> cashes = new ArrayList<>();
        try{
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM \"Cash\" WHERE \"UserID\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cash cash = new Cash();
                cash.setCashID(resultSet.getInt("CashID"));
                cash.setCashBalance(resultSet.getInt("Balance"));
                cash.setCashName(resultSet.getString("Name"));
                cash.setCashDescription(resultSet.getString("Description"));
                cashes.add(cash);
            }
            return cashes;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}