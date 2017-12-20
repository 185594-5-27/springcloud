package com.feign.entity;

/*
* 类描述：
* @auther linzf
* @create 2017/12/20 0020 
*/
public class User {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(){
        super();
    }

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }

}
