package com.expenses.expenses.controllers;

import com.expenses.expenses.implement.IncomeImp;
import com.expenses.expenses.interfaces.IncomeInt;
import com.expenses.expenses.models.Income;
import org.springframework.web.bind.annotation.*;

@RestController
public class IncomeController {
    IncomeInt inc = new IncomeImp();

    @GetMapping("/income/{id}")
    public Income findOneIncome(@PathVariable(value = "id")long id){
        return inc.getOneIncome(id);
    }

    @PostMapping("/addincome")
    public void addIncome(@RequestBody Income income){
        inc.addIncome(income);
    }
    //update an expense
    @PutMapping("/updateincome")
    public void updateIncome(@RequestBody Income income){
        inc.updateIncome(income);
    }

    //delete an expense
    @DeleteMapping("/deleteincome/{id}")
    public void deleteIncome(@PathVariable(value = "id") long id){
        inc.deleteIncome(id);
    }

}
