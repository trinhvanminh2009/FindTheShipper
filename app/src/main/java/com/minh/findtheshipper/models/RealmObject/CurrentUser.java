package com.minh.findtheshipper.models.RealmObject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trinh on 7/13/2017.
 * This user not including list orders and list order saved
 */

public class CurrentUser extends RealmObject {
    @PrimaryKey
    private String email;
    private String name;
    private String phoneNumber;
    private String avatar;
    private String gender;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
