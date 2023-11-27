package com.expenses.expenses.interfaces;

import com.expenses.expenses.models.Expenses;

import java.util.ArrayList;

public interface ExpensesInt {
    //get all expenses for a user and return array list of their expenses
    public ArrayList<Expenses> getAllExpenses(long userId);
    //get one expense return expense model
    public Expenses getOneExpense(long id);
    //add expense to a user void
    public void addExpense(Expenses exp);
    //update expense void
    public void updateExpense(Expenses exp);
    //delete expense void
    public void deleteExpense(long id);
    //returns nested array list hold the totals of all income and expensens based on a single user
    public ArrayList<ArrayList<Double>> getMonthTotals(long userId);
}
