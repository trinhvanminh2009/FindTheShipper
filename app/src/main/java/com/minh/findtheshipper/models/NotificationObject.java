package com.minh.findtheshipper.models;

/**
 * Created by trinh on 6/16/2017.
 *
 */

public class NotificationObject {

    private String notificationID;
    private int icon;
    private String content;
    private String title;
    private String dateTime;
    private boolean showAgain;



    public NotificationObject() {
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public boolean isShowAgain() {
        return showAgain;
    }

    public void setShowAgain(boolean showAgain) {
        this.showAgain = showAgain;
    }
}
