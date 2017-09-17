package com.minh.findtheshipper.helpers;

/**
 * Created by trinh on 7/11/2017.
 */

public class EncodingFireBase {

    public EncodingFireBase() {
    }

    public String encodeString(String string) {
        return string.replace(".", ",");
    }

    public String decodeString(String string) {
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

    public String getEmailFromUserID(String result) {
        String[] split = result.split("_");
        return split[1];
    }
}
