package org.example.Repositories;
import org.example.Connections.DBConnection;
import org.example.Models.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    public List<Card> getCardsByUserId(int userId) {
        List<Card> cards = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM \"Card\" WHERE \"UserID\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setCardID(resultSet.getInt("CardID"));
                card.setName(resultSet.getString("Name"));
                card.setBalance(resultSet.getInt("Balance"));
                card.setNumber(resultSet.getLong("Number"));
                card.setMonth(resultSet.getInt("Month"));
                card.setYear(resultSet.getInt("Year"));
                cards.add(card);
            }
            return cards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCard(int userId, String name, int balance, long number, int month, int year) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO \"Card\"(\"UserID\", \"Name\", \"Balance\", \"Number\", \"Month\", \"Year\") VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, name);
            statement.setInt(3, balance);
            statement.setLong(4, number);
            statement.setInt(5, month);
            statement.setInt(6, year);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
