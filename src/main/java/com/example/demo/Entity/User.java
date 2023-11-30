package com.example.demo.Entity;

public class User {
    private int id;
    private String name;
    private int age;
    private int mobile;

    public User(int id, String name, int age, int mobile) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMobile() {
        return this.mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

}
