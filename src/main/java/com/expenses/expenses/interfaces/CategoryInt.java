package com.expenses.expenses.interfaces;

import com.expenses.expenses.models.Categories;

import java.util.ArrayList;

public interface CategoryInt {

    //view all categories for a particular user
    public ArrayList<Categories> allCategories(long userId);
    //add category
    public void addCategory();
    //extra
    //delete category
    public void deleteCategory(long id);
}
