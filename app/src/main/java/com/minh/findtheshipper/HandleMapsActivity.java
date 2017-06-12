package com.minh.findtheshipper;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.minh.findtheshipper.helpers.DirectionHelpers;
import com.minh.findtheshipper.helpers.MapDragHelpers;
import com.minh.findtheshipper.helpers.listeners.DirectionFinderListeners;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListView;
import com.minh.findtheshipper.models.ListControl;
import com.minh.findtheshipper.models.Route;
import com.minh.findtheshipper.utils.MapDragUtils;
import com.minh.findtheshipper.utils.PermissionUtils;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;


public class HandleMapsActivity extends AppCompatActivity  implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback, DirectionFinderListeners{

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    @BindView(R.id.toolBar)Toolbar toolbar;
    @BindView(R.id.listAction) ListView listPlaces;
    @BindView(R.id.txtDistance)TextView txtDistance;
    @BindView(R.id.txtTime)TextView txtTime;
    @BindView(R.id.btnOK) ImageButton btnOk;
    @BindView(R.id.btnAdd)ImageButton btnPlace;
    @BindView(R.id.txtCash) TextView txtCash;
    @BindView(R.id.layoutItem) LinearLayout layoutItem;
    @BindView(R.id.btnCreateNewOrder) Button btnCreateNewOrder;
    @BindView(R.id.layoutCreateNewOrder) LinearLayout layoutCreateNewOrder;
    @BindView(R.id.layoutControl)LinearLayout layoutControl;
    @BindView(R.id.btnCancelOrder)Button btnCancelOrder;
    private String[] listProfile = new String[4];
    private boolean showPermissionDeniedDialog = false;
    private List<Marker>startMarkers = new ArrayList<>();
    private List<Marker>finishMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private ArrayList<ListControl> listControls;
    private CustomAdapterListView adapterListView;
    private int itemClicked = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_maps);

        ButterKnife.bind(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listProfile = getIntent().getStringArrayExtra("profile");
        listControls = new ArrayList<>();
        listControls.add(new ListControl(R.drawable.ic_starting_point, getResources().getString(R.string.start_place)));
        listControls.add(new ListControl(R.drawable.ic_finish_point, getResources().getString(R.string.finish_place)));
        adapterListView = new CustomAdapterListView(this, listControls);
        listPlaces.setAdapter(adapterListView);
        NavigationDrawer();


    }

    private void sendRequest(){
        String start = listControls.get(0).getContent();
        String finish = listControls.get(1).getContent();
        if(start.isEmpty())
        {
            TastyToast.makeText(this,getResources().getString(R.string.start_place),TastyToast.LENGTH_LONG,TastyToast.WARNING);
            return;

        }
        if(finish.isEmpty())
        {
            TastyToast.makeText(this,getResources().getString(R.string.finish_place),TastyToast.LENGTH_LONG,TastyToast.WARNING);
            return;
        }

        try {
            new DirectionHelpers((DirectionFinderListeners) this, start, finish).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.btnCancelOrder)
    public void cancelOrder()
    {
        btnCreateNewOrder.setVisibility(View.GONE);
        layoutCreateNewOrder.setVisibility(View.GONE);
        layoutControl.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.btnCreateNewOrder)
    public void createNewOrder()
    {
        btnCreateNewOrder.setVisibility(View.GONE);
        layoutCreateNewOrder.setVisibility(View.VISIBLE);
        layoutControl.setVisibility(View.GONE);
    }

    @OnItemClick(R.id.listAction)
    public void listViewClicked(AdapterView<?>parent, View view , int position, long id)
    {
        final ListControl listControl = listControls.get(position);
        if (listControl.getIdIcon() == R.drawable.ic_starting_point)
        {
            btnOk.setVisibility(View.VISIBLE);
            btnPlace.setVisibility(View.VISIBLE);
            layoutCreateNewOrder.setVisibility(View.GONE);
            btnCreateNewOrder.setVisibility(View.GONE);


            TastyToast.makeText(HandleMapsActivity.this,getResources().getString( R.string.choosing_starting_point),TastyToast.LENGTH_LONG,TastyToast.INFO);
            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {

                    LatLng center = mMap.getCameraPosition().target;
                    String tempAddress = getCompleteAddressString(center.latitude, center.longitude);
                    listControls.set(0,new ListControl(R.drawable.ic_starting_point,tempAddress));
                    listPlaces.setAdapter(adapterListView);
                    itemClicked = 1;

                }
            });
        }
        if(listControl.getIdIcon() == R.drawable.ic_finish_point)
        {
            btnCreateNewOrder.setVisibility(View.GONE);
            btnOk.setVisibility(View.VISIBLE);
            btnPlace.setVisibility(View.VISIBLE);
            layoutCreateNewOrder.setVisibility(View.GONE);
            TastyToast.makeText(HandleMapsActivity.this,getResources().getString( R.string.choosing_finish_point),TastyToast.LENGTH_LONG,TastyToast.INFO);
            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    LatLng center = mMap.getCameraPosition().target;
                    String tempAddress = getCompleteAddressString(center.latitude, center.longitude);
                    listControls.set(1,new ListControl(R.drawable.ic_finish_point,tempAddress));
                    listPlaces.setAdapter(adapterListView);
                    itemClicked = 2;
                }
            });

        }
    }


    @OnClick(R.id.btnOK)
    public void getAddress()
    {
        if(itemClicked == 1)
        {
            btnCreateNewOrder.setVisibility(View.GONE);
            layoutCreateNewOrder.setVisibility(View.GONE);
            TastyToast.makeText(HandleMapsActivity.this,getResources().getString( R.string.choosing_finish_point),TastyToast.LENGTH_LONG,TastyToast.INFO);
            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {

                    LatLng center = mMap.getCameraPosition().target;
                    String tempAddress = getCompleteAddressString(center.latitude, center.longitude);
                    listControls.set(1,new ListControl(R.drawable.ic_finish_point,tempAddress));
                    listPlaces.setAdapter(adapterListView);
                    itemClicked = 2;


                }
            });

        }
        if(itemClicked == 2)
        {
            sendRequest();
            btnOk.setVisibility(View.GONE);
            btnPlace.setVisibility(View.GONE);
            layoutItem.setVisibility(View.VISIBLE);
            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {

                }
            });
            TastyToast.makeText(HandleMapsActivity.this,getResources().getString( R.string.choosing_places_success),TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
            btnCreateNewOrder.setVisibility(View.VISIBLE);
        }
        else {

        }


    }



    private void NavigationDrawer() {
        Uri myUri = Uri.parse(listProfile[3]);
        Log.d("myTags",myUri.toString());
        new DrawerBuilder().withActivity(this).build();
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Create new order ");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Created order");
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Tutorials");
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("Your profile");
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("About us");
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Version");
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("Settings");
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName("Logout");
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_home)
                .addProfiles(
                        new ProfileDrawerItem().withName(listProfile[2]).withEmail(listProfile[0]).withIcon(myUri)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,item4,item5,item6,item7,item8
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        return false;
                    }
                })
                .build();

        result.setSelection(1);
        result.setSelection(item2);
        result.setSelection(1, true);
        item1.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_create_new));
        item2.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_created));
        item3.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_tutorials));
        item4.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_your_profile));
        item5.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_about));
        item6.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_version));
        item7.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_settings));
        item8.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_logout));
        result.openDrawer();
        result.closeDrawer();
        result.getDrawerLayout();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng HCMCity = new LatLng(10.766333, 106.694036);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HCMCity, 18));
        updateMyLocation();

    }

    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, "Map is not ready yet", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateMyLocation() {
        if (!checkReady()) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {

            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION, false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
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

        } else {
            showPermissionDeniedDialog = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (showPermissionDeniedDialog) {
            PermissionUtils.PermissionDeniedDialog
                    .newInstance(false).show(getSupportFragmentManager(), "dialog");
            showPermissionDeniedDialog = false;
        }
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this ,"Please wait...","Finding direction...",true);
        if(startMarkers != null)
        {
            for(Marker marker: startMarkers)
            {
                marker.remove();
            }
        }
        if(finishMarkers != null)
        {
            for(Marker marker: finishMarkers)
            {
                marker.remove();
            }
        }
        if(polylinePaths != null)
        {
            for(Polyline polyline : polylinePaths)
            {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        startMarkers = new ArrayList<>();
        finishMarkers = new ArrayList<>();
        for(Route route: routes)
        {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation,16));
            txtDistance.setText(route.distance.text);
            txtTime.setText(route.duration.text);
            int tempKilometers = parseStringToDouble(route.distance.text).intValue();
            txtCash.setText(tempKilometers*5+"K VNĐ");
            startMarkers.add(mMap.addMarker(new MarkerOptions().title(route.startAddress)
            .position(route.startLocation).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))));
            finishMarkers.add(mMap.addMarker(new MarkerOptions().title(route.endAddress)
                    .position(route.endLocation).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));



            PolylineOptions polylineOptions = new PolylineOptions().geodesic(true).color(Color.GREEN).width(10);
            for(int i = 0 ; i < route.points.size(); i++)
            {
                polylineOptions.add(route.points.get(i));
            }
            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    private String getCompleteAddressString(double latitude, double longitude)
    {
        String fullAddress = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude,1);
            if(addresses != null)
            {
                Address address = addresses.get(0);
                StringBuilder  stringBuilder = new StringBuilder("");
                for(int i = 0 ; i <address.getMaxAddressLineIndex(); i++)
                {
                    stringBuilder.append(address.getAddressLine(i)).append(",");
                }
                stringBuilder.append(address.getAddressLine(address.getMaxAddressLineIndex())).append(".");
                fullAddress = stringBuilder.toString();

            }
            else {
                TastyToast.makeText(this, "Not found your location ! Please check again!",TastyToast.LENGTH_LONG,TastyToast.INFO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullAddress;
    }


    private Double parseStringToDouble(String tempString)
    {
        double result = 0;
        String[] string1 = tempString.split("km");
        result = Double.parseDouble(string1[0]);
        if(result <= 1.0)
        {
            return 1.0;
        }
        return result;
    }


}
