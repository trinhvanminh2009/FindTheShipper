package com.minh.findtheshipper;

/**
 * Created by trinh on 7/11/2017.
 */

public class EncodingFirebase {

    public EncodingFirebase() {
    }

    public String encodeString(String string) {
        return string.replace(".", ",");
    }

    public String decodeString(String string) {
        return string.replace(",", ".");
    }
}
