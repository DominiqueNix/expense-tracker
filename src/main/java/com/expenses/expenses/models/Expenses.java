package com.expenses.expenses.models;

public class Expenses{
    private long id;
    private String type;
    private String name;
    private double price;
    private String date;
    private long categoryId;
    private long userId;
    public Expenses(){};

    public Expenses(long id, String type, String name, double price, String date, long categoryId, long userId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.date = date;
        this.categoryId = categoryId;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
