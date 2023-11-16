package com.expenses.expenses.interfaces;

import com.expenses.expenses.models.Categories;

public interface CategoryInt {

    //view all categories for a particular user
//    public ArrayList<Categories> allCategories(long userId);
    //add category
    public void addCategory(Categories cat);
    //extra
    //delete category
    public void deleteCategory(long id);
}
