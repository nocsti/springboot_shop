package com.tfotu.shop.entity;

import java.math.BigInteger;

public class User {
    private Integer id;
    private String mobile;
    private String password;
    private String passwordRandstr;
    private String name;
    private Integer age;
    private Integer sex;
    private BigInteger regTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRandstr() {
        return passwordRandstr;
    }

    public void setPasswordRandstr(String passwordRandstr) {
        this.passwordRandstr = passwordRandstr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigInteger getRegTime() {
        return regTime;
    }

    public void setRegTime(BigInteger regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", passwordRandstr='" + passwordRandstr + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", regTime=" + regTime +
                '}';
    }
}
