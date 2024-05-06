package org.example;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(8080);
        get("/hello", (req, res) -> "Hello, world!");
        post("/hello", (req, res) -> {
            String name = req.queryParams("name");
            return "Hello, " + name + "!";
        });
        get("/hello/:name", (req, res) -> {
            String name = req.params(":name");
            return "Hello, " + name + "!";
        });
    }
}