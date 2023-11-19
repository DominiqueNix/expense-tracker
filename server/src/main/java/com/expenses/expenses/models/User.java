package com.expenses.expenses.models;

import java.util.ArrayList;

public class User {
    private long id;
    private String username;
    private String password;
    private ArrayList<Expenses> expenses;

    // private ArrayList<Categories> categories;

    private int loggedIn;

    public User(){};

    public User(long id, String username, String password, ArrayList<Expenses> expenses, int loggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.expenses = expenses;
        // this.categories = categories;
        this.loggedIn = loggedIn;
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


    public ArrayList<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expenses> expenses) {
        this.expenses = expenses;
    }
    // public ArrayList<Categories> getCategories() {
    //     return categories;
    // }

    // public void setCategories(ArrayList<Categories> categories) {
    //     this.categories = categories;
    // }
    public int getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(int loggedIn) {
        this.loggedIn = loggedIn;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
