package com.minh.findtheshipper.models;

import io.realm.RealmObject;

/**
 * Created by trinh on 6/22/2017.
 */

public class Dislike extends RealmObject {
    private String dislikeID;
    private String dateTime;
    private User user;

    public String getDislikeID() {
        return dislikeID;
    }

    public void setDislikeID(String dislikeID) {
        this.dislikeID = dislikeID;
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
