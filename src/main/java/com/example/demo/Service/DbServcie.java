package com.example.demo.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRowMapper;
import com.example.demo.Repository.UserRepository;

@Service
public class DbServcie implements UserRepository {
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<User> getUsers() {
        List <User> userList = db.query("SELECT * FROM TestVasu", new UserRowMapper());
        ArrayList<User> users = new ArrayList<>(userList);

        return users;
    }
    @Override
    public User getUserById(int userId) {
        try{
        User user = db.queryForObject("SELECT * FROM TestVasu where id = ?", new UserRowMapper(), userId);
        return user;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public User addUser(User user) {
        db.update("INSERT INTO TestVasu(name,age,mobile) values(?,?,?)", user.getName(), user.getAge(), user.getMobile());
        User savedUser = db.queryForObject("SELECT * FROM TestVasu where name = ? and age = ? and mobile =?", new UserRowMapper(), user.getName(),user.getAge(),user.getMobile());
        return savedUser;
    }
    @Override
    public User updateUser(int userId, User user) {
        if(user.getName() != null){
            db.update("Update TestVasu SET name=? where id=?", user.getName(), userId);
        }
        if(user.getAge() !=0){
            db.update("Update TestVasu SET age=? where id=?", user.getAge(), userId);
        }
        if(user.getMobile() != 0){
            db.update("Update TestVasu SET mobile=? where id=?", user.getMobile(), userId);
        }
        
        return getUserById(userId);
    }
    @Override
    public void deleteUser(int userId) {
        db.update("DELETE FROM TestVasu WHERE id = ?", userId);
        
    }
}
