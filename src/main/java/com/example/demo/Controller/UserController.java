package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.User;
import com.example.demo.Service.DbServcie;

@RestController
public class UserController {
    // UserService userService = new UserService();
    @Autowired
    public DbServcie userService;
    @GetMapping("/users")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/createuser")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/updateuser/{userId}")
    public User updateUserById(@PathVariable("userId") int userId, @RequestBody User user){
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        userService.deleteUser(userId);
    }
}
