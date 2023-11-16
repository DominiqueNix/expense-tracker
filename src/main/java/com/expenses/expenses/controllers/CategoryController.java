package com.expenses.expenses.controllers;

import com.expenses.expenses.implement.CategoryImp;
import com.expenses.expenses.interfaces.CategoryInt;
import com.expenses.expenses.models.Categories;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    CategoryInt cat = new CategoryImp();
    @PostMapping("/addcategory")
    public void addCategory(@RequestBody Categories categories){
        cat.addCategory(categories);
    }

    @DeleteMapping("/deletecategory/{id}")
    public void deleteCategory(@PathVariable(value = "id") long id){
        cat.deleteCategory(id);
    }
}
