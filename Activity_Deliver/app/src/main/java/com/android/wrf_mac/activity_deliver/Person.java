package com.android.wrf_mac.activity_deliver;

import java.io.Serializable;

/**
 * Created by wrf_mac on 2017/7/8.
 */

public class Person implements Serializable {
    static final long serialVersionUID = 3453521L;

    private String name;
    private int age;
    private String address;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
