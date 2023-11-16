package com.expenses.expenses.implement;

import com.expenses.expenses.DBConnection;
import com.expenses.expenses.interfaces.CategoryInt;
import com.expenses.expenses.models.Categories;

import java.sql.*;

public class CategoryImp implements CategoryInt {

    @Override
    public void addCategory(Categories cat) {
        Connection con = DBConnection.connect();
        try {
            PreparedStatement pstmt = con.prepareStatement("insert into categories values(null, ?, ?)");
            pstmt.setString(1, cat.getName());
            pstmt.setLong(2, cat.getUserId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCategory(long id) {
        Connection con = DBConnection.connect();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from categories where id="+id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
