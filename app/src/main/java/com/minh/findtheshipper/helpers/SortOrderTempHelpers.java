package com.minh.findtheshipper.helpers;

import android.content.Context;

import com.minh.findtheshipper.models.OrderTemp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by trinh on 7/5/2017.
 * Sort temp order follow by time newest
 */

public class SortOrderTempHelpers implements Comparator<OrderTemp> {
    @Override
    public int compare(OrderTemp o1, OrderTemp o2) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        try {
            return  df.parse(o2.getDateTime()).compareTo(df.parse(o1.getDateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
