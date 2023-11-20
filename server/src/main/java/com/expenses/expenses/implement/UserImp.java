package com.expenses.expenses.implement;

import com.expenses.expenses.DBConnection;
import com.expenses.expenses.interfaces.UserInt;
import com.expenses.expenses.models.*;

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
                user.setLoggedIn(rs.getInt("loggedIn"));
                allUsers.add(user);
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("password"));
                System.out.println(rs.getString("loggedIn"));
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
            PreparedStatement pstmt = con.prepareStatement("insert into users values(null, ?, ?, 0)");
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
                    PreparedStatement pstmt = con.prepareStatement("insert into users values(null, ?, ?, 1)");
                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    int result = pstmt.executeUpdate();
                if(result != 0){
                    System.out.println("User added");
                    PreparedStatement pstmtGetNewUser = con.prepareStatement("select * from users where username=?");
                    pstmtGetNewUser.setString(1, username);

                    ResultSet rs2 = pstmtGetNewUser.executeQuery();
                    while(rs2.next()){
//                        System.out.println(rs2.getInt("id"));
//                        System.out.println(rs2.getString("username"));
                        user.setId(rs2.getInt("id"));
                        user.setUsername(rs2.getString("username"));
                        user.setLoggedIn(rs2.getInt("loggedIn"));
                    }
//                        user.setLoggedIn(1);
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
        ArrayList<Expenses> expenses= new ArrayList<>();
        Connection con = DBConnection.connect();
        //selecting all from user where the username from the database = username user input
//        String query = "select username from users";
        try {
            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                String existingUserName = rs.getString("username");
            System.out.println(username + " username");
            boolean doesUserExist = doesUserExist(username);
            System.out.println(doesUserExist + " doesExist");
                if (doesUserExist) {
                    PreparedStatement pstmtUserInfo = con.prepareStatement("select * from users where username=?");
                    pstmtUserInfo.setString(1, username);
                    ResultSet rs2 = pstmtUserInfo.executeQuery();
                    while(rs2.next()){
                        String usersPass = rs2.getString("password");
                        System.out.println(usersPass.equals(password) + "password corrent (before)");
                        if(usersPass.equals(password)){
                            System.out.println(usersPass.equals(password) + "password corrent (after)");
                            int res = stmt.executeUpdate("update users set loggedIn = 1 where id="+rs2.getInt("id"));
                            System.out.println(res + "update command");
                            if(res != 0){
                                user.setId(rs2.getInt("id"));
                                user.setUsername(rs2.getString("username"));
                                user.setLoggedIn(1);
                                System.out.println(rs2.getInt("id") + " line 153");
                                System.out.println(rs2.getString("username") + " line 154");
//                                ResultSet rs4 = stmt.executeQuery("select * from expenses where userId =" + rs2.getInt("id"));
//                                while(rs4.next()){
//                                    Expenses expense = new Expenses();
//                                    expense.setId(rs4.getLong("id"));
//                                    expense.setType(rs4.getString("type"));
//                                    expense.setName(rs4.getString("name"));
//                                    expense.setPrice(rs4.getDouble("price"));
//                                    expense.setCategoryId(rs4.getLong("categoryId"));
//                                    expense.setUserId(rs4.getLong("userId"));
//                                    expenses.add(expense);
//                                }
//                                user.setExpenses(expenses);
                            }
                        }
                    }
                } else {
                    System.out.println("user does not exist");
                }
//            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(user);
        return user;
    }

    public User findOneUser(long id){
        Connection con = DBConnection.connect();
        User user = new User();
        ArrayList<Expenses> expenses= new ArrayList<>();
        ArrayList<Categories> categories= new ArrayList<>();
        try{
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select id, username, loggedIn from users where id="+id);
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
//            user.setUsername(rs.getString("username"));
            user.setLoggedIn(rs.getInt("loggedIn"));
            ResultSet rs3 = stmt.executeQuery("select * from expenses where userId =" +id);
            while(rs3.next()){
                Expenses expense = new Expenses();
                expense.setId(rs3.getLong("id"));
                expense.setType(rs3.getString("type"));
                expense.setName(rs3.getString("name"));
                expense.setPrice(rs3.getDouble("price"));
                expense.setDate(rs3.getString("date"));
                expense.setCategory(rs3.getString("category"));
                expense.setUserId(rs3.getLong("userId"));
                expenses.add(expense);
            }
            user.setExpenses(expenses);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public void deleteUser(long id){
        Connection con = DBConnection.connect();
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from users where id="+id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void logout(long id){
        Connection con = DBConnection.connect();

        try{
            Statement stmt = con.createStatement();
            stmt.executeQuery("update users set loggedIn = 0 where id="+id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
