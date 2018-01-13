package com.minh.findtheshipper.Shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
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
import com.minh.findtheshipper.models.Route;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FollowShipperActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionFinderListeners {

    private GoogleMap mMap;
    private android.support.v7.widget.Toolbar toolbar;
    private String currentShipper;
    String[]arrGetValues = new String[3];
    private List<Marker> startMarkers = new ArrayList<>();
    private List<Marker> finishMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private String TAG = "FollowShipper";

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_follow_shipper);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            showToolBar();
            getCurrentShipper();
            changeColorStatusBar();
        }catch (Exception e){
            Log.e(TAG, "onCreate: "+e.toString() );
        }
    }

    private void getCurrentShipper() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        arrGetValues = bundle.getStringArray("values");
        if(arrGetValues != null){
            currentShipper = arrGetValues[0];
            String startPlace = arrGetValues[1];
            String finishPlace = arrGetValues[2];
            showPathOnMap(startPlace, finishPlace);

        }else{
            currentShipper = bundle.getString("shipper");
        }

    }

    private void showShipperOnMap() {
        if (currentShipper != null) {
            String key = EncodingFirebase.encodeString(currentShipper);
            final DatabaseReference databaseReferenceUser = FirebaseDatabase.getInstance().getReference("user").child(key);
            databaseReferenceUser.addValueEventListener(new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mMap.clear();
                    double latitude = dataSnapshot.child("Last Latitude").getValue(Double.class);
                    double longitude = dataSnapshot.child("Last Longitude").getValue(Double.class);
                    String name = dataSnapshot.child("Name").getValue(String.class);
                    LatLng latLng = new LatLng(latitude, longitude);
                    Log.e("Here","Minh");
                     mMap.addCircle(new CircleOptions()
                            .center(latLng)
                            .radius(100)
                            .strokeColor(Color.RED));

                    mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.ic_delivery_man)).position(latLng).title(name));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void showToolBar() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        showShipperOnMap();
    }


    private void showPathOnMap(String startPlace, String finishPlace) {
        try {
            new DirectionHelpers(this, startPlace, finishPlace).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

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
           // LatLngBounds bounds = builder.build();
           // mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
            //End Zoom path fit with maps
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

    private void changeColorStatusBar() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

    }
}
