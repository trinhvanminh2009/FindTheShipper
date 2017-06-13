package com.minh.findtheshipper.models;

import java.util.Calendar;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


/**
 * Created by trinh on 6/13/2017.
 */

public class Order extends RealmObject{
    @PrimaryKey
    private String orderID;
    private String status;
    private String startPoint;
    private String finishPoint;
    private String advancedMoney;
    private String shipMoney;
    private String note;
    private String distance;
    private String phoneNumber;
    private Calendar dateTime;
    private int like;
    private int dislike;


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getFinishPoint() {
        return finishPoint;
    }

    public void setFinishPoint(String finishPoint) {
        this.finishPoint = finishPoint;
    }

    public String getAdvancedMoney() {
        return advancedMoney;
    }

    public void setAdvancedMoney(String advancedMoney) {
        this.advancedMoney = advancedMoney;
    }

    public String getShipMoney() {
        return shipMoney;
    }

    public void setShipMoney(String shipMoney) {
        this.shipMoney = shipMoney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
}
