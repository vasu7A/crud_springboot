package com.example.demo.Service;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

public class UserService implements UserRepository {
    private HashMap<Integer, User> hmap = new HashMap<>();
    int uId = 3;

    public UserService() {
        User u1 = new User(1, "vasu", 27, 12345);
        User u2 = new User(2, "kalyan", 25, 12365);

        hmap.put(u1.getId(), u1);
        hmap.put(u2.getId(), u2);
    }

    @Override
    public ArrayList<User> getUsers() {
        Collection<User> userCollection = hmap.values();
        ArrayList<User> users = new ArrayList<>(userCollection);

        return users;
    }

    @Override
    public User getUserById(int userId) {
        User user = hmap.get(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        user.setId(uId);
        hmap.put(uId, user);

        uId += 1;
        return user;
    }

    @Override
    public User updateUser(int userId, User user) {

        User existingUser = hmap.get(userId);
        if (existingUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getAge() != 0) {
            existingUser.setAge(user.getAge());
        }
        if(user.getMobile() != 0){
            existingUser.setMobile(user.getMobile());
        }

        return existingUser;
    }

    @Override
    public void deleteUser(int userId){
        User user = hmap.get(userId);

        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else{
            hmap.remove(userId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
