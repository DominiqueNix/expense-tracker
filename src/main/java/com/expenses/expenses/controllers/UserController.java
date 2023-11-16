package com.expenses.expenses.controllers;

import com.expenses.expenses.implement.UserImp;
import com.expenses.expenses.interfaces.UserInt;
import com.expenses.expenses.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    UserInt user = new UserImp();

    @GetMapping("/users")
    @ResponseBody
    public List<User> allUsers(){
        return user.allUsers();
    }

    @PostMapping("/newUsers")
    @ResponseBody
    public void addNew(@RequestBody User newUser){
        user.addUser(newUser.getUsername(), newUser.getPassword());
    }
    @PostMapping("/signup")
    @ResponseBody
    public User signup(@RequestBody User newUser){
//        System.out.println(newUser.getUsername() + " " + newUser.getPassword());
        return user.signUp(newUser.getUsername(), newUser.getPassword());
    }
    @GetMapping("/login")
    @ResponseBody
    public User login(@RequestBody User existingUser){
        return user.login(existingUser.getUsername(), existingUser.getPassword());
    }
}
