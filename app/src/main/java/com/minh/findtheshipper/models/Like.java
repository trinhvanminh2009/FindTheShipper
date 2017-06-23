package com.minh.findtheshipper.models;

import io.realm.RealmObject;

/**
 * Created by trinh on 6/22/2017.
 */

public class Like extends RealmObject {
    private String likeID;
    private String dateTime;
    private User user;

    public String getLikeID() {
        return likeID;
    }

    public void setLikeID(String likeID) {
        this.likeID = likeID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
