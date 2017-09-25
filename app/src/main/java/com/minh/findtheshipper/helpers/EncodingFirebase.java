package com.minh.findtheshipper.helpers;

/**
 * To decode from server
 * Created by trinh on 7/11/2017.
 */

public class EncodingFireBase {

    public EncodingFireBase() {
    }

    public static String encodeString(String string) {
        return string.replace(".", ",");
    }

    public static String decodeString(String string) {
        return string.replace(",", ".");
    }

    public static String getShortAddress(String string) {
        String result;
        String[] arrayString = string.split(",");
        if (arrayString.length > 2) {
            result = arrayString[0] + ", " + arrayString[1] + ", " + arrayString[2] + ".";
        } else {
            result = string;
        }
        return result;
    }

    public static String getEmailFromUserID(String result) {
        String[] split = result.split("_");
        return split[1];
    }

    public static String convertToRightEmail(String email){
        email = getEmailFromUserID(email);
        return email.replaceAll(",",".");
    }
}
