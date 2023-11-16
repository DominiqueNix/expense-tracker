package com.expenses.expenses.models;

public class Income extends Transactions{
    public Income(){};
    public Income(long id, String name, double price, String date, long categoryId, long userId) {
        super(id, name, price, date, categoryId, userId);
    }
}
