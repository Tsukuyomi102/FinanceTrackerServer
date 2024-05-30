package org.example.Tests;

import org.example.Models.User;
import org.example.Repositories.UserRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Order(1)
public class UserRepositoryTest {

    @Test
    public void testAddUser() {
        UserRepository userRepository = new UserRepository();
        userRepository.addUser("TestUser", "TestEmail@gmail.com", "password123");
        User user = userRepository.loginUser("TestEmail@gmail.com", "password123");
        assertEquals("TestUser", user.getName());
    }

    @Test
    public void testLoginUser() {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.loginUser("TestEmail@gmail.com", "password123");
        assertEquals("TestUser", user.getName());
    }

    @Test
    public void testGetUserById() {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.getUserById(2);
        assertEquals("TestUser", user.getName());
    }

    @Test
    public void testGetUserIdByEmail() {
        UserRepository userRepository = new UserRepository();
        int userId = userRepository.getUserIdByEmail("TestEmail@gmail.com");
        assertEquals(2, userId);
    }
}
