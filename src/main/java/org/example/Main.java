package org.example;
import com.google.gson.Gson;
import org.example.Models.Card;
import org.example.Models.User;
import org.example.Repositories.CardRepository;
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
        post("/user/:userId/card", (req, res) -> {
            int userId = Integer.parseInt(req.params(":userId"));
            String name = req.queryParams("name");
            int balance = Integer.parseInt(req.queryParams("balance"));
            long number = Long.parseLong(req.queryParams("number"));
            int month = Integer.parseInt(req.queryParams("month"));
            int year = Integer.parseInt(req.queryParams("year"));
            CardRepository cardRepository = new CardRepository();
            cardRepository.addCard(userId, name, balance, number, month, year);
            return 1;
        });

        get("/user/:userId/card", (req, res) -> {
            int userId = Integer.parseInt(req.params(":userId"));
            CardRepository cardRepository = new CardRepository();
            List<Card> cards = cardRepository.getCardsByUserId(userId);
            if (cards != null && !cards.isEmpty()) {
                Gson gson = new Gson();
                String json = gson.toJson(cards);
                res.type("application/json");
                return json;
            } else {
                res.status(404);
                return "User not found or user has no cards!";
            }
        });
    }
}
