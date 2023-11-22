package com.expenses.expenses.interfaces;

import com.expenses.expenses.models.User;

import java.util.List;

public interface UserInt {

    //get all users (for testing purposes)
    public List<User> allUsers();

    //get one user (for testing purposes)
    public void addUser(String username, String password);

    //create new (if user does not exist)and find id
    public User signUp(String username, String password);

    //check if user exists, then return their id
    public User login(String username, String password);

    //find one user based on id
    public User findOneUser(long id);

    //delete user based on their id
    public void deleteUser(long id);

    //update loggedIn property of user
    public void logout(long id);

    //extra!!
    //update user
}
