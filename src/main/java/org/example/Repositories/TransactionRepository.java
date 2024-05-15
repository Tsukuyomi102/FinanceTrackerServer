package org.example.Repositories;

import org.example.Connections.DBConnection;
import org.example.Models.Card;
import org.example.Models.Cash;
import org.example.Models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    public void addTransaction(int userId, int cardId, int cashId, boolean isIncome, boolean isCreditCard, String name, String category, int amount, String date) {
        try {
            Connection connection = DBConnection.getConnection();
            String query = "INSERT INTO \"Transaction\"(\"UserID\", \"CardID\", \"CashID\", \"IsIncome\", \"IsCreditCard\", \"Name\", \"Category\", \"Amount\", \"Date\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, cardId);
            statement.setInt(3, cashId);
            statement.setBoolean(4, isIncome);
            statement.setBoolean(5, isCreditCard);
            statement.setString(6, name);
            statement.setString(7, category);
            statement.setInt(8, amount);
            statement.setString(9, date);
            statement.executeUpdate();
            if (cardId != -1 && isCreditCard) {
                int newBalance = calculateNewCardBalance(cardId, amount, isIncome);
                String updateQuery = "UPDATE \"Card\" SET \"Balance\" = ? WHERE \"CardID\" = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, newBalance);
                updateStatement.setInt(2, cardId);
                updateStatement.executeUpdate();
            } else {
                int newBalance = calculateNewCashBalance(cashId, amount, isIncome);
                String updateQuery = "UPDATE \"Cash\" SET \"Balance\" = ? WHERE \"CashID\" = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, newBalance);
                updateStatement.setInt(2, cashId);
                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionsByEmail(String email) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT t.*, c.\"Number\" as CardNumber, cash.\"Name\" as CashName FROM \"Transaction\" t " +
                    "INNER JOIN \"User\" u ON t.\"UserID\" = u.\"UserID\" " +
                    "LEFT JOIN \"Card\" c ON t.\"CardID\" = c.\"CardID\" " +
                    "LEFT JOIN \"Cash\" cash ON t.\"CashID\" = cash.\"CashID\" " +
                    "WHERE u.\"Email\" = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(resultSet.getInt("TransactionID"));
                transaction.setIncome(resultSet.getBoolean("IsIncome"));
                transaction.setCreditCard(resultSet.getBoolean("IsCreditCard"));
                transaction.setTransactionName(resultSet.getString("Name"));
                transaction.setCategory(resultSet.getString("Category"));
                transaction.setAmount(resultSet.getInt("Amount"));
                transaction.setDate(resultSet.getString("Date"));
                transaction.setCardNumber(resultSet.getLong("CardNumber"));
                transaction.setCashName(resultSet.getString("CashName"));
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int calculateNewCardBalance(int cardId, int amount, boolean isIncome) {
        CardRepository cardRepository = new CardRepository();
        Card card = cardRepository.getCardById(cardId);

        if (card != null) {
            int currentBalance = card.getCardBalance();
            int newBalance;

            if (isIncome) {
                newBalance = currentBalance + amount;
            } else {
                newBalance = currentBalance - amount;
            }

            return newBalance;
        }
        return -1;
    }

    private int calculateNewCashBalance(int cashId, int amount, boolean isIncome){
        CashRepository cashRepository = new CashRepository();
        Cash cash = cashRepository.getCashById(cashId);

        if (cash != null) {
            int currentBalance = cash.getCashBalance();
            int newBalance;

            if (isIncome) {
                newBalance = currentBalance + amount;
            } else {
                newBalance = currentBalance - amount;
            }

            return newBalance;
        }
        return -1;
    }
}
