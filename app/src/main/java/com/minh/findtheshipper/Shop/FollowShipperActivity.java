package com.minh.findtheshipper.Shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.EncodingFirebase;

public class FollowShipperActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private android.support.v7.widget.Toolbar toolbar;
    private String currentShipper;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_shipper);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        showToolBar();
        getCurrentShipper();

    }

    private void getCurrentShipper() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        currentShipper = bundle.getString("shipper");

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

                    Circle circle = mMap.addCircle(new CircleOptions()
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
}
