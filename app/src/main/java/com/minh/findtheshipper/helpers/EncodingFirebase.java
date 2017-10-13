package com.minh.findtheshipper.helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * To decode from server
 * Created by trinh on 7/11/2017.
 */

public class EncodingFirebase {


    public EncodingFirebase() {
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

    public static String getCompleteAddressString(Context context,double latitude, double longitude) {
        String fullAddress = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address address = addresses.get(0);
            StringBuilder stringBuilder = new StringBuilder("");
            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                stringBuilder.append(address.getAddressLine(i)).append(",");
            }
            stringBuilder.append(address.getAddressLine(address.getMaxAddressLineIndex())).append(".");
            fullAddress = stringBuilder.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullAddress;
    }
}
