package com.minh.findtheshipper.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by trinh on 6/8/2017.
 * Support to handle Route
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public String startAddress;
    public LatLng startLocation;
    public LatLng stopLocation;
    public LatLng endLocation;
    public List<LatLng> points;

}
