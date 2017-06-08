package com.minh.findtheshipper;

import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;
import com.sdsmdg.tastytoast.TastyToast;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by trinh on 6/7/2017.
 */

public class DistanceMapsActivity extends BaseMapsActivity implements GoogleMap.OnMarkerDragListener {
    private TextView textView;
    private Marker markerA;
    private Marker markerB;
    private Polyline polyline;

    @Override
    protected int getLayoutId() {
        return R.layout.distance_maps;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        showDistance();
        updatePolyline();
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        showDistance();
        updatePolyline();
    }

    @Override
    protected void startMap() {
        textView = (TextView)findViewById(R.id.textView);
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(10.746885, 106.676320), 10));
        getMap().setOnMarkerDragListener(this);
        markerA = getMap().addMarker(new MarkerOptions().position(new LatLng(10.777307, 106.701409)).draggable(true).snippet("and snippet").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        markerB = getMap().addMarker(new MarkerOptions().position(new LatLng(10.746885, 106.676320)).draggable(true).snippet("and snippet").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        polyline = getMap().addPolyline(new PolylineOptions().geodesic(true));
        TastyToast.makeText(DistanceMapsActivity.this, "Drag the markers!",TastyToast.LENGTH_LONG,TastyToast.CONFUSING);
        showDistance();
    }

    public void showDistance()
    {
        double distance = SphericalUtil.computeDistanceBetween(markerA.getPosition(), markerB.getPosition());
        textView.setText("About" + formatNumberDistance(distance) );

    }

    private String formatNumberDistance(double distance) {
        String unit = "m";
        if(distance <1)
        {
            distance *=1000;
            unit = "mm";
        }
        else if(distance >1000)
        {
            distance /=1000;
            unit = "km";
        }
        return String.format("%4.3f%s",distance,unit);
    }

    private void updatePolyline(){
        polyline.setPoints(Arrays.asList(markerA.getPosition(),markerB.getPosition()));
    }


}
