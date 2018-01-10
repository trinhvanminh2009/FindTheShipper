package com.minh.findtheshipper.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.User;
import com.sdsmdg.tastytoast.TastyToast;

import io.realm.Realm;

public class LocationService extends Service {
    private static final String TAG = "Error";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;
    private Realm realm;

    private class LocationListener implements android.location.LocationListener {
        Location mLastLocation;

        private LocationListener(String provider) {
            Log.e(TAG, "LocationListener " + provider);
            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e(TAG, "onLocationChanged: " + location);
            mLastLocation.set(location);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String currentAddress = EncodingFirebase.getCompleteAddressString(getApplicationContext(), latitude, longitude);
           // TastyToast.makeText(getApplicationContext(), "Location updated:" + currentAddress, TastyToast.LENGTH_SHORT, TastyToast.INFO);
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
            mDatabase.child(EncodingFirebase.encodeString(getCurrentUser()
                    .getEmail())).child("Last Latitude").setValue(latitude);
            mDatabase.child(EncodingFirebase.encodeString(getCurrentUser()
                    .getEmail())).child("Last Longitude").setValue(longitude);
            mDatabase.child(EncodingFirebase.encodeString(getCurrentUser()
                    .getEmail())).child("Online").setValue(true);
        }


        @Override
        public void onProviderDisabled(String provider) {
            Log.e(TAG, "onProviderDisabled: " + provider);
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
            mDatabase.child(EncodingFirebase.encodeString(getCurrentUser()
                    .getEmail())).child("Online").setValue(false);
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.e(TAG, "onStatusChanged: by service " + provider);
        }
    }

    LocationService.LocationListener[] mLocationListeners = new LocationService.LocationListener[]{
            new LocationService.LocationListener(LocationManager.GPS_PROVIDER),
            new LocationService.LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
         initRealm();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {

            for (LocationService.LocationListener mLocationListener : mLocationListeners) {
                try {
                    mLocationManager.removeUpdates(mLocationListener);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    public void initRealm() {
        Realm.init(LocationService.this);
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).equalTo("email", currentUser.getEmail()).findFirst();
    }

}