package com.expenses.expenses.models;

import java.util.ArrayList;

public class Categories {
    private long id;
    private String name;
    private long userId;

    private ArrayList<Transactions> transactions;
    public Categories(){};

    public Categories(long id, String name, long userId, ArrayList<Transactions> transactions) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.transactions = transactions;
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
}
