package org.example.Models;

public class Cash {
    private int CashID;
    private int cashBalance;
    private String cashName;
    private String cashDescription;

    public void setCashID(int cashID) {
        this.CashID = cashID;
    }

    public void setCashBalance(int cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void setCashDescription(String cashDescription) {
        this.cashDescription = cashDescription;
    }

    public void setCashName(String cashName) {
        this.cashName = cashName;
    }

    public String getCashName() {
        return this.cashName;
    }

    public int getCashID() {
        return this.CashID;
    }

    public int getCashBalance() {
        return this.cashBalance;
    }

    public String getCashDescription() {
        return this.cashDescription;
    }
}