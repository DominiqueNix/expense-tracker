package com.expenses.expenses;

import com.expenses.expenses.implement.ExpensesImp;
import com.expenses.expenses.implement.UserImp;
import com.expenses.expenses.interfaces.ExpensesInt;
import com.expenses.expenses.interfaces.UserInt;

import java.sql.*;

public class DBConnection {
    public static Connection connect(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:sqlite:expenses");

            Statement stmt = con.createStatement();
            stmt.setQueryTimeout(30);

//            stmt.executeUpdate("drop table if exists test");
//            stmt.executeUpdate("create table test(id integer primary key autoincrement, name varchar(255))");
//            stmt.executeUpdate("insert into test values(null, 'Fred')");
//            stmt.executeUpdate("insert into test values(null, 'Sarah')");
//
//            ResultSet rs = stmt.executeQuery("select * from test");
//            while(rs.next()){
//                System.out.println("id= " + rs.getInt("id"));
//                System.out.println("name= " + rs.getString("name"));
//            }

//            stmt.executeUpdate("drop table users");
//            stmt.executeUpdate("drop table categories");
            stmt.executeUpdate("create table if not exists users(id integer primary key autoincrement, username varchar(255), password varchar(255))");
            stmt.executeUpdate("create table if not exists categories(id integer primary key autoincrement, name varchar(255), userId integer references users(id))");
//            stmt.executeUpdate("drop table income");
//            stmt.executeUpdate("drop table expenses");
            stmt.executeUpdate("create table if not exists income(id integer primary key autoincrement, name varchar(255), price int,date varchar(255), categoryId integer references categories(id), userId integer references users(id))");
            stmt.executeUpdate("create table if not exists expenses(id integer primary key autoincrement, name varchar(255), price int,date varchar(255), categoryId integer references categories(id), userId integer references users(id))");
//



//
//            ResultSet rs = stmt.executeQuery("select * from income");
//            while(rs.next()){
//                System.out.println("id= " + rs.getInt("id"));
//                System.out.println("name= " + rs.getString("name"));
//                System.out.println("price= " + rs.getInt("price"));
//                System.out.println("date= " + rs.getString("date"));
//                System.out.println("catId= " + rs.getInt("categoryId"));
//                System.out.println("userId= " + rs.getInt("userId"));
//            }
//
//            ResultSet rs2 = stmt.executeQuery("select * from expenses");
//            while(rs2.next()){
//                System.out.println("id= " + rs2.getInt("id"));
//                System.out.println("name= " + rs2.getString("name"));
//                System.out.println("price= " + rs2.getInt("price"));
//                System.out.println("date= " + rs2.getString("date"));
//                System.out.println("catId= " + rs2.getInt("categoryId"));
//                System.out.println("userId= " + rs2.getInt("userId"));
//            }


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
//
//    public static void main(String[] args) throws SQLException {
////        connect();
//       Connection con = connect();
//
//        ExpensesInt exp = new ExpensesImp();
//        UserInt user = new UserImp();
//        user.addUser("Tom", "iloVecAts5");
//        user.signUp("Teddy", "test123");
//
//
//
//        user.allUsers();
//
//
//    }
}
