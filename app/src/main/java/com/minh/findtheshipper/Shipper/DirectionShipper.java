package com.minh.findtheshipper.Shipper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.DirectionHelpers;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.listeners.DirectionFinderListeners;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.Route;
import com.minh.findtheshipper.models.RealmObject.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class DirectionShipper extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListeners {

    private GoogleMap mMap;
    private List<Marker> startMarkers = new ArrayList<>();
    private List<Marker> finishMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private Realm realm;
    private String[] placeToGetOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_shipper);
        initRealm();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Value from DetailOrderHistoryActivity
        placeToGetOrder = getIntent().getStringArrayExtra("place");

    }

    public void initRealm() {
        Realm.init(this);
        realm = null;
        realm = Realm.getDefaultInstance();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng HCMCity = new LatLng(10.766333, 106.694036);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HCMCity, 11));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(HCMCity));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
       /* String currentPlaceByGPS = EncodingFirebase.getCompleteAddressString(DirectionShipper.this,
                getCurrentLocationFromGPS().getLatitude(), getCurrentLocationFromGPS().getLongitude());
        showPathOnMap( currentPlaceByGPS,placeToGetOrder[0]);*/
        updateUsersOnlineFromServer();

    }

    @Override
    public void onDirectionFinderStart() {
        if (startMarkers != null) {
            for (Marker marker : startMarkers) {
                marker.remove();
            }
        }
        if (finishMarkers != null) {
            for (Marker marker : finishMarkers) {
                marker.remove();
            }
        }
        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    private void updateUsersOnlineFromServer() {

        DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference();
        userDatabase.child("user").addValueEventListener(new ValueEventListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Marker marker;
                mMap.clear();
                String key = EncodingFirebase.encodeString(getCurrentUser().getEmail());
                String userName = dataSnapshot.child(key).child("Name").getValue(String.class);
                double latitude = dataSnapshot.child(key).child("Last Latitude").getValue(Double.class);
                double longitude = dataSnapshot.child(key).child("Last Longitude").getValue(Double.class);
                MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude, longitude))
                        .title(userName).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delivery_man));
                marker = mMap.addMarker(markerOptions);
                animateMarker(marker, new LatLng(latitude, longitude), false);

                showPathOnMap(EncodingFirebase.getCompleteAddressString(DirectionShipper.this,
                        latitude,longitude),placeToGetOrder[0]);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),16.0f));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }

    private void showPathOnMap(String startPlace, String finishPlace) {
        try {
            new DirectionHelpers(this, startPlace, finishPlace).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        polylinePaths = new ArrayList<>();
        startMarkers = new ArrayList<>();
        finishMarkers = new ArrayList<>();
        for (Route route : routes) {
            // Start Zoom path fit with maps
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(route.startLocation);
            builder.include(route.endLocation);
            LatLngBounds bounds = builder.build();
          //  mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
            //End Zoom path fit with maps
            if (placeToGetOrder != null) {
             /*   txtDistance.setText(route.distance.text);
                txtTime.setText(route.duration.text);*/
                startMarkers.add(mMap.addMarker(new MarkerOptions().title(route.startAddress)
                        .position(route.startLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_start_place))));
                finishMarkers.add(mMap.addMarker(new MarkerOptions().title(route.endAddress)
                        .position(route.endLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_destination))));
                PolylineOptions polylineOptions = new PolylineOptions().geodesic(true).color(Color.GREEN).width(12);
                for (int i = 0; i < route.points.size(); i++) {
                    polylineOptions.add(route.points.get(i));
                }
                polylinePaths.add(mMap.addPolyline(polylineOptions));
            }

        }
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }


}
