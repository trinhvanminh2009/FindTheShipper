package com.minh.findtheshipper.models;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Minh on 6/1/2017.
 */

public class User extends RealmObject {
    @PrimaryKey
    private int userID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String avatar;
    private RealmList<Order> orderArrayList;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RealmList<Order> getOrderArrayList() {
        return orderArrayList;
    }

    public void setOrderArrayList(RealmList<Order> orderArrayList) {
        this.orderArrayList = orderArrayList;
    }
}
