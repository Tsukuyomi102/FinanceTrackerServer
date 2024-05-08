package org.example.Models;

public class Card {
    private int CardID;
    private String name;
    private Integer Balance;
    private Long number;
    private Integer month;
    private Integer year;

    public void setCardID(int cardID) {
        this.CardID = cardID;
    }

    public void setBalance(Integer balance) {
        this.Balance = balance;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
