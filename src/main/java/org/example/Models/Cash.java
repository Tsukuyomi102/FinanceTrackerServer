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
}
