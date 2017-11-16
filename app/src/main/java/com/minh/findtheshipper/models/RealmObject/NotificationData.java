package com.minh.findtheshipper.models.RealmObject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trinh on 11/16/2017.
 * This class support get count notifications for many classes
 */

public class NotificationData extends RealmObject {
    @PrimaryKey
    private String key;
    private int totalNotification;
    private int numberUnread;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTotalNotification() {
        return totalNotification;
    }

    public void setTotalNotification(int totalNotification) {
        this.totalNotification = totalNotification;
    }

    public int getNumberUnread() {
        return numberUnread;
    }

    public void setNumberUnread(int numberUnread) {
        this.numberUnread = numberUnread;
    }
}
