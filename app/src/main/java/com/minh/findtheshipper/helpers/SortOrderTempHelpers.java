package com.minh.findtheshipper.helpers;

import com.minh.findtheshipper.models.OrderTemp;

import java.util.Comparator;

/**
 * Created by trinh on 7/5/2017.
 * Sort temp order follow by time newest
 */

public class SortOrderTempHelpers implements Comparator<OrderTemp> {
    @Override
    public int compare(OrderTemp o1, OrderTemp o2) {
       return o2.getDateTime().compareTo(o1.getDateTime());
    }
}
