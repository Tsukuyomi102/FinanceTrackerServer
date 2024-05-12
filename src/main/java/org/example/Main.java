package org.example;
import com.google.gson.Gson;
import org.example.Models.Card;
import org.example.Models.Cash;
import org.example.Models.User;
import org.example.Repositories.CardRepository;
import org.example.Repositories.CashRepository;
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
    }
}