package io.realm;


public interface OrderRealmProxyInterface {
    public String realmGet$orderID();
    public void realmSet$orderID(String value);
    public String realmGet$status();
    public void realmSet$status(String value);
    public String realmGet$startPoint();
    public void realmSet$startPoint(String value);
    public String realmGet$finishPoint();
    public void realmSet$finishPoint(String value);
    public String realmGet$advancedMoney();
    public void realmSet$advancedMoney(String value);
    public String realmGet$shipMoney();
    public void realmSet$shipMoney(String value);
    public String realmGet$note();
    public void realmSet$note(String value);
    public String realmGet$distance();
    public void realmSet$distance(String value);
    public String realmGet$phoneNumber();
    public void realmSet$phoneNumber(String value);
    public String realmGet$dateTime();
    public void realmSet$dateTime(String value);
    public RealmList<com.minh.findtheshipper.models.Like> realmGet$likes();
    public void realmSet$likes(RealmList<com.minh.findtheshipper.models.Like> value);
    public RealmList<com.minh.findtheshipper.models.Dislike> realmGet$dislikes();
    public void realmSet$dislikes(RealmList<com.minh.findtheshipper.models.Dislike> value);
    public RealmList<com.minh.findtheshipper.models.Comment> realmGet$comments();
    public void realmSet$comments(RealmList<com.minh.findtheshipper.models.Comment> value);
    public Boolean realmGet$saveOrder();
    public void realmSet$saveOrder(Boolean value);
}
