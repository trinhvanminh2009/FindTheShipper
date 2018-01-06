package com.minh.findtheshipper.helpers;

import com.minh.findtheshipper.models.OrderTemp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by trinh on 7/5/2017.
 * Sort temp order follow by time newest
 */

public class SortOrderTempHelpers implements Comparator<OrderTemp> {
    @Override
    public int compare(OrderTemp o1, OrderTemp o2) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        try {
            return df.parse(o2.getDateTime()).compareTo(df.parse(o1.getDateTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
