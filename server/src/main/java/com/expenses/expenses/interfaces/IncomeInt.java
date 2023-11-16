package com.expenses.expenses.interfaces;

import com.expenses.expenses.models.Income;

import java.util.ArrayList;

public interface IncomeInt {
    //get all expenses for a user and return array list of their expenses
    public ArrayList<Income> getAllIncome(long userId);
    //get one expense return expense model
    public Income getOneIncome(long id);
    //add expense to a user void
    public void addIncome(Income income);
    //update expense void
    public void updateIncome(Income income);
    //delete expense void
    public void deleteIncome(long id);
}
