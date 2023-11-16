package com.expenses.expenses.models;

import java.util.ArrayList;

public class Categories {
    private long id;
    private String name;
    private long userId;

    private ArrayList<Expenses> expenses;
    public Categories(){};

    public Categories(long id, String name, long userId, ArrayList<Expenses> expenses) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.expenses = expenses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(ArrayList<Expenses> expenses) {
        this.expenses = expenses;
    }
}
