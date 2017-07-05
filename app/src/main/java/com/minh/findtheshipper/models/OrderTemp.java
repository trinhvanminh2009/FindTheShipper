package com.minh.findtheshipper.models;

/**
 * Created by trinh on 7/5/2017.
 */

public class OrderTemp {
    private String orderID;
    private String status;
    private String startPoint;
    private String finishPoint;
    private String advancedMoney;
    private String shipMoney;
    private String note;
    private String distance;
    private String phoneNumber;
    private String dateTime;

    public OrderTemp() {
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
