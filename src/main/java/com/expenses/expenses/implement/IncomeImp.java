package com.expenses.expenses.implement;

import com.expenses.expenses.DBConnection;
import com.expenses.expenses.interfaces.IncomeInt;
import com.expenses.expenses.models.Income;

import java.sql.*;
import java.util.ArrayList;

public class IncomeImp implements IncomeInt {
    @Override
    public ArrayList<Income> getAllIncome(long userId) {
        Connection con = DBConnection.connect();
        String query = "select * from income where userid="+userId;
        ArrayList manyIncome = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Income income = new Income();
                income.setId(rs.getLong("id"));
                income.setName(rs.getString("name"));
                income.setPrice(rs.getDouble("price"));
                income.setDate(rs.getString("date"));
                income.setCategoryId(rs.getLong("categoryId"));
                income.setUserId(rs.getLong("userId"));
                manyIncome.add(income);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return manyIncome;
    }

    @Override
    public Income getOneIncome(long id) {
        Connection con = DBConnection.connect();
        String query = "select * from income where id="+id;
        Income inc = new Income();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                inc.setId(rs.getLong("id"));
                inc.setName(rs.getString("name"));
                inc.setPrice(rs.getDouble("price"));
                inc.setDate(rs.getString("date"));
                inc.setCategoryId(rs.getLong("categoryId"));
                inc.setUserId(rs.getLong("userId"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return inc;
    }

    @Override
    public void addIncome(Income income) {
        Connection con = DBConnection.connect();

        try{

            PreparedStatement pstmt = con.prepareStatement("insert into income values(null, ?, ?, ?, ?, ?)");
            pstmt.setString(1, income.getName());
            pstmt.setDouble(2, income.getPrice());
            pstmt.setString(3, income.getDate());
            pstmt.setLong(4, income.getCategoryId());
            pstmt.setLong(5, income.getUserId());
            int result = pstmt.executeUpdate();
            if(result != 0){
                System.out.println("Income added");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateIncome(Income income) {
        Connection con = DBConnection.connect();
        String query = "update income set name=?, price=?, date=?, categoryId = ?, userId=? where id="+income.getId();

        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, income.getName());
            pstmt.setDouble(2, income.getPrice());
            pstmt.setString(3, income.getDate());
            pstmt.setLong(4, income.getCategoryId());
            pstmt.setLong(4, income.getCategoryId());
            pstmt.setLong(5, income.getUserId());
            int result = pstmt.executeUpdate();
            if(result != 0){
                System.out.println("Income updated");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteIncome(long id) {
        Connection con = DBConnection.connect();
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from income where id="+id);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
