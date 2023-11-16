package com.expenses.expenses.models;

import java.util.ArrayList;

public class User {
    private long id;
    private String username;
    private String password;
    private ArrayList<Income> income;
    private ArrayList<Expenses> expenses;



    private boolean isLoggedIn;

    public User(){};

    public User(long id, String username, String password, ArrayList<Income> income, ArrayList<Expenses> expenses, boolean isLoggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.income = income;
        this.expenses = expenses;
        this.isLoggedIn = isLoggedIn;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Income> getIncome() {
        return income;
    }

    public void setIncome(ArrayList<Income> income) {
        this.income = income;
    }

    public ArrayList<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expenses> expenses) {
        this.expenses = expenses;
    }
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", income=" + income +
                ", expenses=" + expenses +
                '}';
    }
}
