package org.example.Tests;

import org.example.Models.Cash;
import org.example.Repositories.CashRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashRepositoryTest {

    @Test
    void addCash() {
        CashRepository cashRepository = new CashRepository();
        int userid = 2;
        int balance = 100;
        String name = "Cash test";
        String description = "Testing method";

        cashRepository.addCash(userid, balance, name, description);
        List<Cash> cashList = cashRepository.getCashByUserId(userid);

        assertFalse(cashList.isEmpty());
    }

    @Test
    void getCashByUserId() {
        CashRepository cashRepository = new CashRepository();
        int userid = 2;

        List<Cash> cashList = cashRepository.getCashByUserId(userid);

        assertFalse(cashList.isEmpty());
    }

    @Test
    void getCashById() {
        CashRepository cashRepository = new CashRepository();
        int cashId = 1;

        Cash cash = cashRepository.getCashById(cashId);

        assertNotNull(cash);
    }
}
