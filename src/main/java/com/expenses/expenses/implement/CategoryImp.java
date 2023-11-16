package com.expenses.expenses.implement;

import com.expenses.expenses.DBConnection;
import com.expenses.expenses.interfaces.CategoryInt;
import com.expenses.expenses.models.Categories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryImp implements CategoryInt {
    @Override
    public ArrayList<Categories> allCategories(long userId) {
        //create connection
        //create emplty cat array list
        //create empty tranactions arraylist
        //select all from categories where userid=userid
        //while rs.next, create new category model
        ///add items to model, then add model to arraylist
        //select * from income where categoryid = rs.getId();
        //while rs.next
        //create income model
        //add item
        //add income model to arraylist
        //return arraylist

        Connection con = DBConnection.connect();
        ArrayList<Categories> categories = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from categories where userId=" + userId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return null;
    }

    @Override
    public void addCategory() {

    }

    @Override
    public void deleteCategory(long id) {

    }
}
