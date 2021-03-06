package com.minh.findtheshipper.models.RealmObject;

import io.realm.RealmList;
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
    private String dateTime;
    private String deliveryTime;
    private RealmList<Like> likes;
    private RealmList<Dislike>dislikes;
    private RealmList<Comment> comments;
    private Boolean saveOrder;


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

    public RealmList<Like> getLikes() {
        return likes;
    }
    public void setLikes(RealmList<Like> likes) {
        this.likes = likes;
    }

    public RealmList<Dislike> getDislikes() {
        return dislikes;
    }

    public void setDislikes(RealmList<Dislike> dislikes) {
        this.dislikes = dislikes;
    }

    public RealmList<Comment> getComments() {
        return comments;
    }

    public void setComments(RealmList<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getSaveOrder() {
        return saveOrder;
    }

    public void setSaveOrder(Boolean saveOrder) {
        this.saveOrder = saveOrder;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
