package com.expenses.expenses.models;

public class Expenses extends Transactions{
    public Expenses(){};
    public Expenses(long id, String name, double price, String date, long categoryId, long userId) {
        super(id, name, price, date, categoryId, userId);
    }
}
