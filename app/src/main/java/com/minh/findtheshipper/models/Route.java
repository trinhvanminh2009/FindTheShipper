package com.minh.findtheshipper.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by trinh on 6/8/2017.
 */

public class Route {
    public Distance distance;
    public Duration duration;
    public String endAddress;
    public String startAddress;
    public LatLng startLocation;
    public LatLng endLocation;
    public List<LatLng> points;

}
