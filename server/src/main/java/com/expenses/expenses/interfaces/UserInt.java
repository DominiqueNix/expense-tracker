package com.expenses.expenses.interfaces;

import com.expenses.expenses.models.User;

import java.util.List;

public interface UserInt {
    public List<User> allUsers();

    public void addUser(String username, String password);

    //create new (if user does not exist)and find one
    public User signUp(String username, String password);

    //check if user exists, then return their data
    public User login(String username, String password);
    //find one user based on id
    public User findOneUser(long id);

    public void deleteUser(long id);

    public void logout(long id);

    //extra!!
    //update user
}
