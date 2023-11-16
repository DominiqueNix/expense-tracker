package com.expenses.expenses.implement;

import com.expenses.expenses.DBConnection;
import com.expenses.expenses.interfaces.UserInt;
import com.expenses.expenses.models.Expenses;
import com.expenses.expenses.models.Income;
import com.expenses.expenses.models.User;
import org.sqlite.core.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserImp implements UserInt {
    @Override
    public List<User> allUsers() {
        List<User> allUsers = new ArrayList<>();
        Connection con = DBConnection.connect();
        String query = "select * from users";

        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                allUsers.add(user);
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return allUsers;
    }



    @Override
    public void addUser(String username, String password) {
        Connection con = DBConnection.connect();

        try{
            PreparedStatement pstmt = con.prepareStatement("insert into users values(null, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int result = pstmt.executeUpdate();
            if(result != 0){
                System.out.println("user added");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    public boolean doesUserExist(String username){
        boolean bool = false;
        Connection con = DBConnection.connect();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select username from users");
            while(rs.next()){
                String existingUserName = rs.getString("username");
                if(existingUserName.equals(username)){
                    bool= true;
                } else{
                    bool= false;
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return bool;
    }
    @Override
    public User signUp(String username, String password){
        User user = new User();
        Connection con =DBConnection.connect();
        try{
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("select username from users");

//            while (rs.next()){
//                String existingUserName = rs.getString("username");
//                if(existingUserName.equals(username)){
//                    System.out.println("user already exists");
            boolean doesUserExist = doesUserExist(username);
                if(doesUserExist){
                    System.out.println("user exists");
                } else {
                    PreparedStatement pstmt = con.prepareStatement("insert into users values(null, ?, ?)");
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    int result = pstmt.executeUpdate();
                if(result != 0){
                    System.out.println("User added");
                    PreparedStatement pstmtGetNewUser = con.prepareStatement("select * from users where username=?");
                    pstmtGetNewUser.setString(1, username);

                    ResultSet rs2 = pstmtGetNewUser.executeQuery();
                    while(rs2.next()){
                        System.out.println(rs2.getInt("id"));
                        System.out.println(rs2.getString("username"));
                        user.setId(rs2.getInt("id"));
                        user.setUsername(rs2.getString("username"));
                    }
                    user.setLoggedIn(true);
                    }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public User login(String username, String password){
                User user = new User();
        ArrayList<Income> incomes= new ArrayList<>();
        ArrayList<Expenses> expenses= new ArrayList<>();
        Connection con = DBConnection.connect();
        //selecting all from user where the username from the database = username user input
        String query = "select username from users";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String existingUserName = rs.getString("username");
                if (existingUserName.equals(username)) {
                    PreparedStatement pstmtUserInfo = con.prepareStatement("select * from users where username=?");
                    pstmtUserInfo.setString(1, username);
                    ResultSet rs2 = pstmtUserInfo.executeQuery();
                    while(rs2.next()){
                        String usersPass = rs2.getString("password");
                        if(usersPass.equals(password)){
                            user.setId(rs2.getInt("id"));
                            user.setUsername(rs2.getString("username"));
                            user.setLoggedIn(true);
                            ResultSet rs3 = stmt.executeQuery("select * from income where userId =" + rs2.getInt("id"));

                            while(rs3.next()){
                                Income income = new Income();
                                income.setId(rs3.getLong("id"));
                                income.setName(rs3.getString("name"));
                                income.setPrice(rs3.getDouble("price"));
                                income.setCategoryId(rs3.getLong("categoryId"));
                                income.setUserId(rs3.getLong("userId"));
                                incomes.add(income);
                            }
                            user.setIncome(incomes);
                            ResultSet rs4 = stmt.executeQuery("select * from expenses where userId =" + rs2.getInt("id"));

                            while(rs4.next()){
                                Expenses expense = new Expenses();
                                expense.setId(rs4.getLong("id"));
                                expense.setName(rs4.getString("name"));
                                expense.setPrice(rs4.getDouble("price"));
                                expense.setCategoryId(rs4.getLong("categoryId"));
                                expense.setUserId(rs4.getLong("userId"));
                                expenses.add(expense);
                            }
                            user.setExpenses(expenses);
                            break;
                        }
                    }
                } else {
                    System.out.println("user does not exist");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(user);
        return user;
    }
}
