package com.expenses.expenses.models;

public class Expenses{
    private long id;
    private String type;
    private String name;
    private double price;
    private String date;
    private String category;
    private long userId;
    
    public Expenses(){};

    public Expenses(long id, String type, String name, double price, String date, String category, long userId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.date = date;
        this.category = category;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}