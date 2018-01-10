package com.minh.findtheshipper.common;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by trinh on 1/7/2018.
 * Use for show location of shipper on the road ship item
 */

public class DialogLocation extends android.support.v4.app.DialogFragment implements OnMapReadyCallback {
    private String key = "";
    private Circle circle;
    private MarkerOptions markerOptions;
    private GoogleMap mMap;
    private View view;


    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().getAttributes().windowAnimations = R.style.MyDialogAnimation;
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
        try {
            view = inflater.inflate(R.layout.dialog_fragment_location, container, false);

        } catch (Exception e) {
            Log.e("Error", "View duplicated");
        }
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment supportMapFragment = (SupportMapFragment) this.getFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
        Bundle bundle = this.getArguments();
        key = bundle.getString("userEmail");


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (!TextUtils.isEmpty(key)) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(key);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressWarnings("ConstantConditions")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double longitude = dataSnapshot.child("Last Longitude").getValue(Double.class);
                    double latitude = dataSnapshot.child("Last Latitude").getValue(Double.class);
                    String username = dataSnapshot.child("Name").getValue(String.class);
                    if (longitude > 0 && latitude > 0 && username != null) {
                        LatLng latLng = new LatLng(latitude, longitude);
                        moveToCurrentLocation(latLng, username);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
        Log.e("Minh","View run to here");

    }

    private void moveToCurrentLocation(LatLng currentLocation, String username) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        circle = mMap.addCircle(new CircleOptions()
                .center(currentLocation).radius(1000).strokeColor(Color.RED).fillColor(Color.GREEN));
        markerOptions = new MarkerOptions().position(currentLocation).title(username)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_delivery_man));


    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.comment_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
    }


}
