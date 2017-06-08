package com.minh.findtheshipper.utils;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.minh.findtheshipper.R;

/**
 * Created by trinh on 6/6/2017.
 */

public class GPSTracker extends Service implements LocationListener {
    private final Context context;
    private boolean isGPSEnabled = false;
    private boolean canGetLocation = false;
    private boolean isNetworkEnable = false;
    private Location location;
    private double latitude;
    private double longitude;
    private static final long MIN_DISTANCE_FOR_UPDATES = 10;
    private static final long MIN_TIME_UPDATES = 1000 * 60;
    protected LocationManager locationManager;

    public GPSTracker(Context context) {
        this.context = context;
       getLocation();
    }

    public Location getLocation() {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled  ) {
            showSettingLocationAlert();
        }
        if(!isNetworkEnable)
        {
            showSettingNetworkAlert();
        }
        if(isGPSEnabled)
        {
            if(location == null)
            {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_UPDATES,
                        MIN_DISTANCE_FOR_UPDATES, this);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_UPDATES,MIN_DISTANCE_FOR_UPDATES,this);

            }
            if(locationManager != null)
            {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location != null)
                {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
        }
        else {
            this.canGetLocation = true;
            if (isNetworkEnable) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Log.d("Permission3", "updateMyLocation: in MapsD");
                    return null;
                }
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_UPDATES,
                        MIN_DISTANCE_FOR_UPDATES, this);
            }
            if(locationManager != null)
            {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(location != null)
                {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }

        }
        return location;
    }

    public void stopUsingGPS(){
        if(locationManager != null)
        {
            locationManager.removeUpdates(GPSTracker.this);
        }
    }

    public double getLatitude()
    {
        if(location != null){
           latitude= location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude()
    {
        if(location != null)
        {
            longitude = location.getLongitude();
        }
        return longitude;
    }

    public boolean isCanGetLocation()
    {
        return canGetLocation;
    }

    public void showSettingLocationAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("GPS Settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }


    public void showSettingNetworkAlert()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Wifi settings");
        alertDialog.setMessage("Wifi is not available. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK);
                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
