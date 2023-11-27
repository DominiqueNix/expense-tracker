package com.expenses.expenses.controllers;

import com.expenses.expenses.implement.ExpensesImp;
import com.expenses.expenses.interfaces.ExpensesInt;
import com.expenses.expenses.models.Expenses;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class ExpenseController {
    ExpensesInt exp = new ExpensesImp();

    @GetMapping("/expenses/{id}")
    public Expenses findOneExpense(@PathVariable(value = "id") long id){
        return exp.getOneExpense(id);
    }

    @GetMapping("yearly/{userId}")
    public  ArrayList<ArrayList<Double>> getYearly(@PathVariable(value = "userId") long userId){
        return exp.getMonthTotals(userId);
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

