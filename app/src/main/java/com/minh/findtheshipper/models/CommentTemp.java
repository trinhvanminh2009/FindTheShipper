package com.minh.findtheshipper.models;

import com.minh.findtheshipper.models.User;

/**
 * Created by trinh on 7/5/2017.
 */

public class CommentTemp {
    private String idComment;
    private String Content;
    private String dateTime;
    private String userName;

    public CommentTemp() {
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
