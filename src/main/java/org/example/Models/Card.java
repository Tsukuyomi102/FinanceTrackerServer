package org.example.Models;

public class Card {
    private int CardID;
    private String cardName;
    private Integer cardBalance;
    private Long cardNumber;
    private Integer month;
    private Integer year;

    public void setCardID(int cardID) {
        this.CardID = cardID;
    }

    public void setCardBalance(Integer cardBalance) {
        this.cardBalance = cardBalance;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getCardNumber() {
        return this.cardNumber;
    }

    public int getCardID() {
        return this.CardID;
    }

    public Integer getCardBalance() {
        return this.cardBalance;
    }
}
