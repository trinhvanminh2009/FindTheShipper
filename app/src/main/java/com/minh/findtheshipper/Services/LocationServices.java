package com.minh.findtheshipper.Services;

import android.Manifest;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by trinh on 10/12/2017.
 * This Class to update current location into server
 */

public class LocationServices extends IntentService implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener{
    private GoogleApiClient googleApiClient;
    private Location location;
    private LocationManager locationManager;
    private LocationRequest locationRequest;
    private String TAG = "Error";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *  Used to name the worker thread, important only for debugging.
     */

    public LocationServices() {
        super("Update location");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startLocationUpdates();
        location = com.google.android.gms.location.LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if(location == null){
            startLocationUpdates();
        }
        if(location != null){
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String currentAddress = EncodingFirebase.getCompleteAddressString(this, latitude, longitude);
            TastyToast.makeText(this, currentAddress,TastyToast.LENGTH_SHORT,TastyToast.INFO);

        }else {
            TastyToast.makeText(this, "Location not Detected",TastyToast.LENGTH_SHORT,TastyToast.INFO);

        }
    }

    private void startLocationUpdates() {
        //Create location request
        locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1).setFastestInterval(1);//Check Interval and FastestInterval
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        com.google.android.gms.location.LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);
        Log.d(TAG,"Request -------->");

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "Connection Suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("Connected failed","Connected failed"+connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String currentAddress = EncodingFirebase.getCompleteAddressString(this, latitude, longitude);
        TastyToast.makeText(this,"Location updated:"+ currentAddress,TastyToast.LENGTH_SHORT,TastyToast.INFO);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
