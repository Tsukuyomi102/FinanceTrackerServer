package org.example;
import org.example.Models.User;
import org.example.Repositories.UserRepository;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        post("/user", (req, res) -> {
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            UserRepository user = new UserRepository();
            user.addUser(name, email, password);
            return "User added successfully";
        });

        get("/user/:id", (req, res) -> {
            int userId = Integer.parseInt(req.params(":id"));
            UserRepository userRepository = new UserRepository();
            User user = userRepository.getUserById(userId);
            return user.toString();
        });
    }
}
