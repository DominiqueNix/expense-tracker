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

    //method that checks if a user exists (used in sign up and login methods)
    public boolean doesUserExist(String username){
       ArrayList<Boolean> bool = new ArrayList<>();
        Connection con = DBConnection.connect();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select username from users");
            while(rs.next()){
                System.out.println(rs.getString("username"));
                String existingUserName = rs.getString("username");
                if(existingUserName.equals(username)){
                    bool.add(true);
                } else{
                    bool.add(false);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        if(bool.contains(true)){
            return true;
        } else {
            return false;
        }
    }

    //check if user exsist, if not, create a new user, then get thier id.
    @Override
    public User signUp(String username, String password){
        User user = new User();
        Connection con =DBConnection.connect();
        try{
            boolean doesUserExist = doesUserExist(username);
            System.out.println(doesUserExist);
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
                        user.setId(rs2.getInt("id"));
                        user.setUsername(rs2.getString("username"));
                        user.setLoggedIn(rs2.getInt("loggedIn"));
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    //checks if user exists, if so, update thier loogedin property and get user info
    @Override
    public User login(String username, String password){
        User user = new User();
        Connection con = DBConnection.connect();
        try {
            Statement stmt = con.createStatement();
            boolean doesUserExist = doesUserExist(username);
                if (doesUserExist) {
                    PreparedStatement pstmtUserInfo = con.prepareStatement("select * from users where username=?");
                    pstmtUserInfo.setString(1, username);
                    ResultSet rs2 = pstmtUserInfo.executeQuery();
                    while(rs2.next()){
                        String usersPass = rs2.getString("password");
                        if(usersPass.equals(password)){
                            int res = stmt.executeUpdate("update users set loggedIn = 1 where id="+rs2.getInt("id"));
                            if(res != 0){
                                user.setId(rs2.getInt("id"));
                                user.setUsername(rs2.getString("username"));
                                user.setLoggedIn(1);
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

//finds all the information (including expenses) for one user
    public User findOneUser(long id){
        Connection con = DBConnection.connect();
        User user = new User();
        ArrayList<Expenses> expenses= new ArrayList<>();
        try{
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select id, username, loggedIn from users where id="+id);
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
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

    //deletes a user based on id
    @Override
    public void deleteUser(long id){
        Connection con = DBConnection.connect();
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from users where id="+id);
            stmt.executeUpdate("delete from expenses where userId="+id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    //updateds users loggedIn property
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
