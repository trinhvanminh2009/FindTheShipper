package io.realm;


public interface UserRealmProxyInterface {
    public String realmGet$email();
    public void realmSet$email(String value);
    public String realmGet$fullName();
    public void realmSet$fullName(String value);
    public String realmGet$phoneNumber();
    public void realmSet$phoneNumber(String value);
    public int realmGet$avatar();
    public void realmSet$avatar(int value);
    public RealmList<com.minh.findtheshipper.models.Order> realmGet$orderArrayList();
    public void realmSet$orderArrayList(RealmList<com.minh.findtheshipper.models.Order> value);
    public RealmList<com.minh.findtheshipper.models.Order> realmGet$orderListSave();
    public void realmSet$orderListSave(RealmList<com.minh.findtheshipper.models.Order> value);
}
