package com.expenses.expenses.implement;

import com.expenses.expenses.DBConnection;
import com.expenses.expenses.interfaces.ExpensesInt;
import com.expenses.expenses.models.Expenses;

import java.sql.*;
import java.util.ArrayList;

public class ExpensesImp implements ExpensesInt {

    //finds all expenses for a particular user 
    @Override
    public ArrayList<Expenses> getAllExpenses(long userId) {
        Connection con = DBConnection.connect();
        String query = "select * from expenses where userid="+userId;
        ArrayList<Expenses> expenses = new ArrayList<>();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Expenses expense = new Expenses();
                expense.setId(rs.getLong("id"));
                expense.setType(rs.getString("type"));
                expense.setName(rs.getString("name"));
                expense.setPrice(rs.getDouble("price"));
                expense.setDate(rs.getString("date"));
                expense.setCategory(rs.getString("category"));
                expense.setUserId(rs.getLong("userId"));
                expenses.add(expense);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return expenses;
    }


    //gets one expense for a user
    @Override
    public Expenses getOneExpense(long id) {
        Connection con = DBConnection.connect();
        String query = "select * from expenses where id="+id;
        Expenses exp = new Expenses();

        try{
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                exp.setId(rs.getLong("id"));
                exp.setName(rs.getString("name"));
                exp.setType(rs.getString("type"));
                exp.setPrice(rs.getDouble("price"));
                exp.setDate(rs.getString("date"));
                exp.setCategory(rs.getString("category"));
                exp.setUserId(rs.getLong("userId"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return exp;
    }

    //adds an expense
    @Override
    public void addExpense(Expenses exp) {
        Connection con = DBConnection.connect();

        try{

            PreparedStatement pstmt = con.prepareStatement("insert into expenses values(null,?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, exp.getType());
            pstmt.setString(2, exp.getName());
            pstmt.setDouble(3, exp.getPrice());
            pstmt.setString(4, exp.getDate());
            pstmt.setString(5, exp.getCategory());
            pstmt.setLong(6, exp.getUserId());
            int result = pstmt.executeUpdate();
            if(result != 0){
                System.out.println("Expense added");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //updates an expense
    @Override
    public void updateExpense(Expenses exp) {
        Connection con = DBConnection.connect();
        String query = "update expenses set type=?, name=?, price=?, date=?, category = ?, userId=? where id="+exp.getId();

        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, exp.getType());
            pstmt.setString(2, exp.getName());
            pstmt.setDouble(3, exp.getPrice());
            pstmt.setString(4, exp.getDate());
            pstmt.setString(5, exp.getCategory());
            pstmt.setLong(6, exp.getUserId());
            int result = pstmt.executeUpdate();
            if(result != 0){
                System.out.println("Expense updated");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    //deletes an expense
    @Override
    public void deleteExpense(long id) {
            Connection con = DBConnection.connect();
            try{
                Statement stmt = con.createStatement();
                stmt.executeUpdate("delete from expenses where id="+id);
            }catch(SQLException e){
                System.out.println(e.getMessage());
        }
    }


    @Override
    public ArrayList<ArrayList<Double>> getMonthTotals(long userId){
        //create two arraylists 
        //first one hold all expenses for a user
        //second one intializes a nested array to integers
            //first array list will hold expenses
            //second arralyist will hold income
        
        //loop though allExpenses array
            //if type === expense
                //check the date (can do a switch statment for every month)
                    //ex. switch date
                            //case: "01"
                                //nestedArray.get(0).set(0, nestedArray.get(0).get(0) + expenses[i]);
        ArrayList<Expenses> allExpenses = new ArrayList<>();
        ArrayList<ArrayList<Double>> nestedArray= new ArrayList<ArrayList<Double>>();
        nestedArray.add(new ArrayList<>());
        nestedArray.add(new ArrayList<>());
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 12; j++){
                nestedArray.get(i).add(0.00);
            }
        }

        Connection con = DBConnection.connect();

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from expenses where userId=" +userId);
            while(rs.next()){
               Expenses expense = new Expenses();
                expense.setId(rs.getLong("id"));
                expense.setType(rs.getString("type"));
                expense.setName(rs.getString("name"));
                expense.setPrice(rs.getDouble("price"));
                expense.setDate(rs.getString("date"));
                expense.setCategory(rs.getString("category"));
                expense.setUserId(rs.getLong("userId"));
                allExpenses.add(expense);
            }


            for(int i = 0; i < allExpenses.size(); i++){
                if(allExpenses.get(i).getType().equals("expense")){
                  if(allExpenses.get(i).getDate().contains("-1-")){
                    nestedArray.get(0).set(0, nestedArray.get(0).get(0) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-2-")){
                    nestedArray.get(0).set(1, nestedArray.get(0).get(1) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-3-")){
                    nestedArray.get(0).set(2, nestedArray.get(0).get(2) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-4-")){
                    nestedArray.get(0).set(3, nestedArray.get(0).get(3) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-5-")){
                    nestedArray.get(0).set(4, nestedArray.get(0).get(4) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-6-")){
                    nestedArray.get(0).set(5, nestedArray.get(0).get(5) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-7-")){
                    nestedArray.get(0).set(6, nestedArray.get(0).get(6) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-8-")){
                    nestedArray.get(0).set(7, nestedArray.get(0).get(7) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-9-")){
                    nestedArray.get(0).set(8, nestedArray.get(0).get(8) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-10-")){
                    nestedArray.get(0).set(9, nestedArray.get(0).get(9) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-11-")){
                    nestedArray.get(0).set(10, nestedArray.get(0).get(10) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-12-")){
                    nestedArray.get(0).set(11, nestedArray.get(0).get(11) + allExpenses.get(i).getPrice());
                  }


                } else{
                    if(allExpenses.get(i).getDate().contains("-1-")){
                    nestedArray.get(1).set(0, nestedArray.get(1).get(0) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-2-")){
                    nestedArray.get(1).set(1, nestedArray.get(1).get(1) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-3-")){
                    nestedArray.get(1).set(2, nestedArray.get(1).get(2) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-4-")){
                    nestedArray.get(1).set(3, nestedArray.get(1).get(3) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-5-")){
                    nestedArray.get(1).set(4, nestedArray.get(1).get(4) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-6-")){
                    nestedArray.get(1).set(5, nestedArray.get(1).get(5) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-7-")){
                    nestedArray.get(1).set(6, nestedArray.get(1).get(6) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-8-")){
                    nestedArray.get(1).set(7, nestedArray.get(1).get(7) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-9-")){
                    nestedArray.get(1).set(8, nestedArray.get(1).get(8) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-10-")){
                    nestedArray.get(1).set(9, nestedArray.get(1).get(9) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-11-")){
                    nestedArray.get(1).set(10, nestedArray.get(1).get(10) + allExpenses.get(i).getPrice());
                  } else if(allExpenses.get(i).getDate().contains("-12-")){
                    nestedArray.get(1).set(11, nestedArray.get(1).get(11) + allExpenses.get(i).getPrice());
                  }
                }
            }


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(nestedArray);
        return nestedArray;
    }

}
