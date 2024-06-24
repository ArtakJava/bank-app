package org.example.entity;

import org.example.menu.MenuSelect;

public class Account implements MenuSelect {
    private long number;
    private long balance;
    private boolean active;
    private long bik;
    private Currency currency;

    public Account(long number, long bik, Currency currency) {
        this.number = number;
        this.balance = 0;
        this.active = true;
        this.bik = bik;
        this.currency = currency;
    }

    public Account(long number, long balance, boolean active, long bik, Currency currency) {
        this.number = number;
        this.balance = balance;
        this.active = active;
        this.bik = bik;
        this.currency = currency;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getBik() {
        return bik;
    }

    public void setBik(long bik) {
        this.bik = bik;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number=" + number +
                ", balance=" + balance +
                ", active=" + active +
                ", bik=" + bik +
                ", currency=" + currency +
                '}';
    }

    @Override
    public String getTitle() {
        return toString();
    }
}