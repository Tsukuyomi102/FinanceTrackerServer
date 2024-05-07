package org.example;
import com.google.gson.Gson;
import org.example.Models.User;
import org.example.Repositories.UserRepository;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
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
    }
}
