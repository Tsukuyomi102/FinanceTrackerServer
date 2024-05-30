package org.example.Models;

public class Transaction {
    private int transactionID;
    private boolean isIncome;
    private boolean isCreditCard;
    private String transactionName;
    private String category;
    private int amount;
    private String date;
    private Long cardNumber;
    private String cashName;


    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setIncome(boolean income) {
        this.isIncome = income;
    }

    public void setCreditCard(boolean creditCard) {
        this.isCreditCard = creditCard;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCashName(String cashName) {
        this.cashName = cashName;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public String getCashName() {
        return cashName;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public boolean isCreditCard() {
        return isCreditCard;
    }

    public boolean isIncome() {
        return isIncome;
    }
}