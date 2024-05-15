package org.example;
import com.google.gson.Gson;
import org.example.Models.Card;
import org.example.Models.Cash;
import org.example.Models.Transaction;
import org.example.Models.User;
import org.example.Repositories.CardRepository;
import org.example.Repositories.CashRepository;
import org.example.Repositories.TransactionRepository;
import org.example.Repositories.UserRepository;

import java.util.List;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        //User requests 
        post("/user", (req, res) -> {
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            UserRepository userRepository = new UserRepository();
            userRepository.addUser(name, email, password);
            return 1;
        });

        post("/login", (req, res) -> {
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            UserRepository userRepository = new UserRepository();
            User user = userRepository.loginUser(email, password);
            if (user != null) {
                Gson gson = new Gson();
                String json = gson.toJson(user);
                res.type("application/json");
                return json;
            } else {
                res.status(404);
                return "User not found!";
            }
        });

        get("/user/:id", (req, res) -> {
            int userId = Integer.parseInt(req.params(":id"));
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserById(userId);
            return user.toString();
        });

        //Card requests
        post("/user/card", (req, res) -> {
            String email = req.queryParams("email");
            String name = req.queryParams("name");
            int balance = Integer.parseInt(req.queryParams("balance"));
            long number = Long.parseLong(req.queryParams("number"));
            int month = Integer.parseInt(req.queryParams("month"));
            int year = Integer.parseInt(req.queryParams("year"));

            UserRepository userRepository = new UserRepository();
            int userId = userRepository.getUserIdByEmail(email);

            if (userId != -1) {
                CardRepository cardRepository = new CardRepository();
                cardRepository.addCard(userId, name, balance, number, month, year);
                return 1;
            } else {
                res.status(404);
                return "User not found!";
            }
        });

        get("/user/:email/card", (req, res) -> {
            String email = req.params(":email");
            UserRepository userRepository = new UserRepository();
            int userId = userRepository.getUserIdByEmail(email);

            if (userId != -1) {
                CardRepository cardRepository = new CardRepository();
                List<Card> cards = cardRepository.getCardsByUserId(userId);
                if (cards != null && !cards.isEmpty()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(cards);
                    res.type("application/json");
                    return json;
                } else {
                    res.status(404);
                    return "User has no cards!";
                }
            } else {
                res.status(404);
                return "User not found!";
            }
        });

        //Cash requests
        post("/user/cash", (req, res) -> {
            String email = req.queryParams("email");
            int balance = Integer.parseInt(req.queryParams("balance"));
            String name = req.queryParams("name");
            String description = req.queryParams("description");

            UserRepository userRepository = new UserRepository();
            int userId = userRepository.getUserIdByEmail(email);

            if (userId != -1) {
                CashRepository cashRepository = new CashRepository();
                cashRepository.addCash(userId, balance, name, description);
                return 1;
            } else {
                res.status(404);
                return "User not found!";
            }
        });

        get("/user/:email/cash", (req, res) -> {
            String email = req.params(":email");
            UserRepository userRepository = new UserRepository();
            int userId = userRepository.getUserIdByEmail(email);

            if (userId != -1) {
                CashRepository cashRepository = new CashRepository();
                List<Cash> cahes = cashRepository.getCashByUserId(userId);
                if (cahes != null && !cahes.isEmpty()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(cahes);
                    res.type("application/json");
                    return json;
                } else {
                    res.status(404);
                    return "User has no cards!";
                }
            } else {
                res.status(404);
                return "User not found!";
            }
        });

        //Transaction requests
        //Transaction requests
        post("/user/transaction", (req, res) -> {
            String email = req.queryParams("email");
            boolean isIncome = Boolean.parseBoolean(req.queryParams("isIncome"));
            boolean isCreditCard = Boolean.parseBoolean(req.queryParams("isCreditCard"));
            String name = req.queryParams("name");
            String category = req.queryParams("category");
            int amount = Integer.parseInt(req.queryParams("amount"));
            String date = req.queryParams("date");
            long cardNumber = Long.parseLong(req.queryParams("cardNumber"));
            String cashName = req.queryParams("cashName");

            UserRepository userRepository = new UserRepository();
            int userId = userRepository.getUserIdByEmail(email);

            if (userId != -1) {
                if (isCreditCard) {
                    CardRepository cardRepository = new CardRepository();
                    List<Card> cards = cardRepository.getCardsByUserId(userId);

                    Card card = cards.stream().filter(c -> c.getCardNumber() == cardNumber).findFirst().orElse(null);

                    if (card != null) {
                        TransactionRepository transactionRepository = new TransactionRepository();
                        transactionRepository.addTransaction(userId, card.getCardID(), -1, isIncome, true, name, category, amount, date);
                        return 1;
                    } else {
                        res.status(404);
                        return "Card not found!";
                    }
                } else {
                    CashRepository cashRepository = new CashRepository();
                    List<Cash> cashes = cashRepository.getCashByUserId(userId);
                    Cash cash = cashes.stream().filter(c -> c.getCashName().equals(cashName)).findFirst().orElse(null);

                    if (cash != null) {
                        TransactionRepository transactionRepository = new TransactionRepository();
                        transactionRepository.addTransaction(userId, -1, cash.getCashID(), isIncome, false, name, category, amount, date);
                        return 1;
                    } else {
                        res.status(404);
                        return "Cash not found!";
                    }
                }
            } else {
                res.status(404);
                return "User not found!";
            }
        });

        get("/user/:email/transactions", (req, res) -> {
            String email = req.params(":email");
            UserRepository userRepository = new UserRepository();
            int userId = userRepository.getUserIdByEmail(email);

            if (userId != -1) {
                TransactionRepository transactionRepository = new TransactionRepository();
                List<Transaction> transactions = transactionRepository.getTransactionsByEmail(email);
                if (transactions != null && !transactions.isEmpty()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(transactions);
                    res.type("application/json");
                    return json;
                } else {
                    res.status(404);
                    return "User has no transactions!";
                }
            } else {
                res.status(404);
                return "User not found!";
            }
        });
    }
}