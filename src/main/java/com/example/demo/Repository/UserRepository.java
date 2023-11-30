package com.example.demo.Repository;

import java.util.*;

import com.example.demo.Entity.User;

public  interface UserRepository {
    ArrayList<User> getUsers();
    User getUserById(int userId);
    User addUser(User user);
    User updateUser(int userId, User user);
    void deleteUser(int userId);
}
