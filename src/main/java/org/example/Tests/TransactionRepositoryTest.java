package org.example.Tests;

import org.example.Models.Card;
import org.example.Models.Cash;
import org.example.Models.Transaction;
import org.example.Repositories.TransactionRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TransactionRepositoryTest {

    @Test
    @Order(1)
    void addTransaction() {
        TransactionRepository transactionRepository = new TransactionRepository();

        int userId = 2;
        int cardId = 3;
        int cashId = -1;
        boolean isIncome = true;
        boolean isCreditCard = true;
        String name = "Test Transaction";
        String category = "Test Category";
        int amount = 100;
        String date = "12 Feb 2024";

        transactionRepository.addTransaction(userId, cardId, cashId, isIncome, isCreditCard, name, category, amount, date);

        List<Transaction> transactions = transactionRepository.getTransactionsByEmail("TestEmail@gmail.com");
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(name, transactions.get(0).getTransactionName());
    }

    @Test
    void getTransactionsByEmail() {
        TransactionRepository transactionRepository = new TransactionRepository();

        List<Transaction> transactions = transactionRepository.getTransactionsByEmail("TestEmail@gmail.com");

        assertNotNull(transactions);
    }

    @Test
    void deleteTransactionAndUpdateBalance() {
        TransactionRepository transactionRepository = new TransactionRepository();

        String email = "TestEmail@gmail.com";
        String transactionName = "Test Transaction";

        boolean result = transactionRepository.deleteTransactionAndUpdateBalance(email, transactionName);

        assertTrue(result);
    }

}
