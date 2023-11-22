package com.expenses.expenses;

import java.sql.*;

public class DBConnection {
    public static Connection connect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:sqlite:server/expenses.db");

            Statement stmt = con.createStatement();
            stmt.setQueryTimeout(30);

        //    stmt.executeUpdate("drop table users");
            stmt.executeUpdate("create table if not exists users(id integer primary key autoincrement, username varchar(255), password varchar(255), loggedIn int)");
        //    stmt.executeUpdate("drop table expenses");
            stmt.executeUpdate("create table if not exists expenses(id integer primary key autoincrement,type varchar(255) ,name varchar(255), price int,date varchar(255), category varchar(255), userId integer references users(id))");


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }

//    public static void main(String[] args) throws SQLException {
    //    connect();
//    }
}
