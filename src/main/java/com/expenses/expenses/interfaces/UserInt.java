package com.expenses.expenses.interfaces;

import com.expenses.expenses.models.User;

import java.util.List;

public interface UserInt {
    public List<User> allUsers();
    //signup -> check if user exists, if not create new user then send user id (create new)
//    public User newUser(String username, String password);
//    //login -> find user and return users id, income, and expenses data (find one)
//    public User login(String username, String password);

    public void addUser(String username, String password);

    //create new (if user does not exist)and find one
    public User signUp(String username, String password);

    //check if user exists, then return their data
    public User login(String username, String password);

    //extra!!
    //update user
    //delete user
}
