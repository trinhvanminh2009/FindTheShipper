package com.minh.findtheshipper.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by trinh on 6/16/2017.
 */

public class NotificationObject  {
    private int icon;
    private String context;



    public NotificationObject( int icon, String context) {

        this.icon = icon;
        this.context = context;
    }



    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
