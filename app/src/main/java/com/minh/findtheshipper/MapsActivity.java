package com.minh.findtheshipper;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListView;
import com.minh.findtheshipper.models.ListControl;
import com.minh.findtheshipper.utils.GPSTrackerUtils;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GPSTrackerUtils gpsTracker;
    private GoogleMap mMap;
    private ArrayList<ListControl> listControls;
    private CustomAdapterListView adapterListView;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.listAction)
    ListView listView;
    private String[] listProfile = new String[4];
    private LocationManager locationManager;
    private static final int MY_PERMISSION_REQUEST_FINE_LOCATION = 101;

    private boolean permissionIsGranted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ButterKnife.bind(this);

        listProfile = getIntent().getStringArrayExtra("profile");
        NavigationDrawer();
        listControls = new ArrayList<>();
        listControls.add(new ListControl(R.drawable.ic_starting_point, "Choose place to start"));
        listControls.add(new ListControl(R.drawable.ic_test, "Choose place to finish"));
        adapterListView = new CustomAdapterListView(this, listControls);
        listView.setAdapter(adapterListView);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @OnItemClick(R.id.listAction)
    public void listViewItemClicked(AdapterView<?> parent, View view, int position, long id) {
        ListControl listControl = listControls.get(position);
        Toast.makeText(this, "Checked on " + listControl.getContent(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode != MY_PERMISSION_REQUEST_FINE_LOCATION)
        {

            return;
        }



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
        updateMyLocation();
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(10.766333, 106.694036);
        showLatitude();
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Ho Chi Minh City"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_FINE_LOCATION);
                Log.d("Permission 0", "updateMyLocation: in MapsA");
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {

                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }

        }
    }

    private boolean checkReady() {
        if (mMap == null) {
            TastyToast.makeText(MapsActivity.this, "The maps not ready", TastyToast.LENGTH_LONG, TastyToast.INFO);
            return false;
        }
        return true;
    }

    private void updateMyLocation() {
        if (!checkReady()) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.d("Permission1", "updateMyLocation: in MapsB");
            mMap.setMyLocationEnabled(true);
            return;
        }
        else {
           /* PermissionUtils.requestPermission(this,MY_PERMISSION_REQUEST_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,false);*/
        }



    }


    public void showLatitude()
    {
        gpsTracker = new GPSTrackerUtils(MapsActivity.this);
        if(gpsTracker.isCanGetLocation())
        {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            TastyToast.makeText(MapsActivity.this, "My Location is" + latitude + ","+ longitude, TastyToast.LENGTH_LONG,TastyToast.SUCCESS);

        }
        else
        {
          //  gpsTracker.showSettingNetworkAlert();
        }
    }



    public void NavigationDrawer()
    {
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


}
