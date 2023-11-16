package com.expenses.expenses.controllers;

import com.expenses.expenses.implement.ExpensesImp;
import com.expenses.expenses.interfaces.ExpensesInt;
import com.expenses.expenses.models.Expenses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpenseController {
    ExpensesInt exp = new ExpensesImp();

    // for now...only need to get one transaction, all transactions are found when I find one user (there is not separe page for finding all transactions)
    @GetMapping("/expenses/{id}")
    public Expenses findOneExpense(@PathVariable(value = "id") long id){
        return exp.getOneExpense(id);
    }

    @PostMapping("/addexpense")
    public void addExpense(@RequestBody Expenses expense){
        exp.addExpense(expense);
    }

    //update an expense
    @PutMapping("/updateexpense")
    public void updateExpense(@RequestBody Expenses expense){
        exp.updateExpense(expense);
    }

    //delete an expense
    @DeleteMapping("/deleteexpense/{id}")
    public void deleteExpense(@PathVariable(value = "id") long id){
        exp.deleteExpense(id);
    }
}