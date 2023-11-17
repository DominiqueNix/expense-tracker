package com.expenses.expenses.controllers;

import com.expenses.expenses.implement.UserImp;
import com.expenses.expenses.interfaces.UserInt;
import com.expenses.expenses.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserInt user = new UserImp();

    @GetMapping("/users")
    public List<User> allUsers(){
        return user.allUsers();
    }

    @PostMapping("/adduser")
    public void addNew(@RequestBody User newUser){
        user.addUser(newUser.getUsername(), newUser.getPassword());
    }
    @PostMapping("/signup")
    public User signup(@RequestBody User newUser){
//        System.out.println(newUser.getUsername() + " " + newUser.getPassword());
        return user.signUp(newUser.getUsername(), newUser.getPassword());
    }
    @PutMapping("/login")
    public User login(@RequestBody User existingUser){
        return user.login(existingUser.getUsername(), existingUser.getPassword());
    }

    @GetMapping("/user/{id}")
    public User findOneUser(@PathVariable(value="id") long id){
        return user.findOneUser(id);
    }

    @DeleteMapping("/users/delete/{id}")
    public void deleteUser(@PathVariable(value="id") long id){
            user.deleteUser(id);
    }
}
