package com.minh.findtheshipper;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.*;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.minh.findtheshipper.helpers.DialogHelpers;
import com.minh.findtheshipper.helpers.DirectionHelpers;
import com.minh.findtheshipper.helpers.listeners.DirectionFinderListeners;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListView;
import com.minh.findtheshipper.models.Comment;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.ListControl;
import com.minh.findtheshipper.models.NotificationObject;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.Route;
import com.minh.findtheshipper.models.User;
import com.minh.findtheshipper.utils.PermissionUtils;
import com.sdsmdg.tastytoast.TastyToast;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class HandleMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback, DirectionFinderListeners{

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private int badgerCount = 10;
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
    @BindView(R.id.editStartPlace) EditText editStartPlace;
    @BindView(R.id.editShipMoney)EditText editShipMoney;
    @BindView(R.id.editNumber)EditText editPhoneNumber;
    @BindView(R.id.btnConfirmCreateOrder) Button btnConfirmCreateOrder;
    @BindView(R.id.editAdvancedMoney) EditText editAdvancedMoney;
    @BindView(R.id.editNote) EditText editNote;
    @BindView(R.id.fragmentMaps)LinearLayout fragmentMaps;
    @BindView(R.id.fragmentShopContainer) FrameLayout fragmentShipper;
    private boolean showPermissionDeniedDialog = false;
    private List<Marker>startMarkers = new ArrayList<>();
    private List<Marker>finishMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private ArrayList<ListControl> listControls = new ArrayList<>();
    private CustomAdapterListView adapterListView;
    private int itemClicked = 0;
    private Realm realm;
    private android.support.v4.app.Fragment fragment = null;
    private long countOrder[] = new long[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_handle_maps);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        realm.init(HandleMapsActivity.this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.created_order);
        initRealm();
        NavigationDrawer(toolbar);
        listControls.add(new ListControl(R.drawable.ic_starting_point, getResources().getString(R.string.start_place)));
        listControls.add(new ListControl(R.drawable.ic_finish_point, getResources().getString(R.string.finish_place)));
        adapterListView = new CustomAdapterListView(HandleMapsActivity.this, listControls);
        listPlaces.setAdapter(adapterListView);
        insertNotification();
        if(findViewById(R.id.fragmentShopContainer) != null)
        {
            if(savedInstanceState != null)
            {
                return;
            }
        }
        //Count order on server before create orders. Because server is thread slow.
        DatabaseReference mDatabaseComment = FirebaseDatabase.getInstance().getReference("order");
        mDatabaseComment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                countOrder[0] = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void NavigationDrawer(Toolbar toolbar) {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        String url = currentUser.getAvatar();
        new DrawerBuilder().withActivity(this).build();
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.create_new_order);
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.created_order);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.tutorials);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.profile);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.about_us);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.version);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.setting);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName(R.string.log_out);
        AccountHeader headerResult = null;

            headerResult = new AccountHeaderBuilder()
                    .withActivity(this)
                    .withHeaderBackground(R.drawable.image_drawer)
                    .addProfiles(
                            new ProfileDrawerItem().withName(currentUser.getName()).withEmail(currentUser.getEmail())
                                    .withIcon(url)
                    )
                    .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                        @Override
                        public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                            return false;
                        }
                    })
                    .build();


        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item5,
                        item6,
                        item7,
                        item8
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        /**I have to visible the layout before replace fragment
                         * Because I did layout before I know how to using Fragment.
                         * But I can change this activity into fragment, this is perfect way
                         * */

                        if(drawerItem.getIdentifier() ==1)
                        {
                            getSupportActionBar().setTitle(R.string.create_new_order);
                            fragmentShipper.setVisibility(View.GONE);
                            fragmentMaps.setVisibility(View.VISIBLE);

                        }
                        if(drawerItem.getIdentifier() == 2)
                        {
                            getSupportActionBar().setTitle(R.string.created_order);
                            fragmentMaps.setVisibility(View.GONE);
                            fragmentShipper.setVisibility(View.VISIBLE);
                            fragment = new ListOrderCreatedFragment();
                        }
                        if(drawerItem.getIdentifier() == 3)
                        {

                        }
                        if(drawerItem.getIdentifier() == 4)
                        {

                        }
                        if(drawerItem.getIdentifier() == 5)
                        {

                        }
                        if(drawerItem.getIdentifier() == 6)
                        {

                        }
                        if(drawerItem.getIdentifier() == 7)
                        {
                            //Only for settings using intent
                            Intent intent = new Intent(HandleMapsActivity.this, SettingsShopPreferences.class);
                            startActivity(intent);
                            return true;
                        }
                        if(drawerItem.getIdentifier() == 8)
                        {

                        }
                        try {
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.replace(R.id.fragmentShopContainer,fragment);
                            transaction.commit();
                        }catch (Exception e)
                        {}
                        return false;
                    }
                })
                .build();


        item1.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_create_new));
        item2.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_list_order));
        item3.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_tutorials));
        item4.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_your_profile));
        item5.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_about));
        item6.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_version));
        item7.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_settings));
        item8.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_logout));
        result.openDrawer();
        result.closeDrawer();
        result.getDrawerLayout();
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

    private Bitmap getFacebookProfilePicture(String url) throws IOException {
        URL imageURL = new URL(url);
        Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        return bitmap;
    }

    private boolean isPhoneNumberValid(String phoneNumber)
    {

        if(phoneNumber.length() >=9 && phoneNumber.length() <=11)
        {


            if( phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) != '0' )
            {
                int countNumber = 0;
                for (int i = 0 ; i < phoneNumber.length(); i++)
                {
                    if(phoneNumber.charAt(i) == '0')
                    {
                        countNumber++;
                    }
                }
                if(countNumber <5)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Only set start place in order form
     */

    public void handleCreateNewOrder()
    {
        editStartPlace.setText(listControls.get(0).getContent());
        editPhoneNumber.setText(getCurrentUser().getPhoneNumber());


    }

    @OnClick(R.id.btnConfirmCreateOrder)
    public void confirmCreateOrder()
    {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(HandleMapsActivity.this, SweetAlertDialog.WARNING_TYPE);
        if(!isPhoneNumberValid(editPhoneNumber.getText().toString()))
        {

            sweetAlertDialog.setTitleText(getResources().getString(R.string.phone_invalid_title));
            sweetAlertDialog.setContentText(getResources().getString(R.string.phone_invalid_summary));
            sweetAlertDialog.show();
            if(editShipMoney.getText().length() == 0)
            {
                sweetAlertDialog.setTitleText(getResources().getString(R.string.money_ship_invalid_title));
                sweetAlertDialog.setContentText(getResources().getString(R.string.money_ship_invalid_summary));
                sweetAlertDialog.show();
            }
        }
        else {
            insertOrder();
        }

    }

    public void insertNotification()
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final NotificationObject notificationObject = realm.createObject(NotificationObject.class,"nf"+countNotification());
                notificationObject.setIcon(R.mipmap.ic_launcher_app);
                notificationObject.setContext(getResources().getString(R.string.start_place));
                realm.insertOrUpdate(notificationObject);
            }
        });
    }

    public long countNotification()
    {
        return realm.where(NotificationObject.class).count();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void insertOrder()
    {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    EncodingFirebase encodingFirebase = new EncodingFirebase();
                    User user = getCurrentUser();
                    final Order order = realm.createObject(Order.class, "order_" + user.getEmail() + "_" + countOrder[0]);
                    order.setStatus(getResources().getString(R.string.order_status));
                    order.setStartPoint("- " + editStartPlace.getText().toString());
                    order.setFinishPoint("- " + listControls.get(1).getContent());
                    order.setAdvancedMoney(getResources().getString(R.string.order_advanced_money) + editAdvancedMoney.getText().toString() + "K VNĐ");
                    order.setDistance(getResources().getString(R.string.order_distance) + txtDistance.getText().toString());
                    order.setShipMoney(getResources().getString(R.string.order_ship_money) + editShipMoney.getText().toString() + "K VNĐ");
                    if (!editNote.getText().toString().equals("")) {
                        order.setNote(getResources().getString(R.string.order_note) + editNote.getText().toString());
                    }
                    order.setDistance(getResources().getString(R.string.order_distance) + txtDistance.getText().toString());
                    order.setPhoneNumber(getResources().getString(R.string.order_phone_number) + editPhoneNumber.getText().toString());
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
                    String date = df.format(Calendar.getInstance().getTime());
                    order.setDateTime(date);
                    order.setSaveOrder(false);
                    realm.insertOrUpdate(order);

                    user.getOrderArrayList().add(order);
                    realm.insertOrUpdate(user);
                    //Post data into server after added to database, have to encoding before post into firebase server
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Status").setValue(order.getStatus());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Start place").setValue(order.getStartPoint());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Finish place").setValue(order.getFinishPoint());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Advanced money").setValue(order.getAdvancedMoney());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Phone number").setValue(order.getPhoneNumber());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Ship Money").setValue(order.getShipMoney());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Note").setValue(order.getNote());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Distance").setValue(order.getDistance());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Datetime").setValue(order.getDateTime());
                    mDatabase.child(encodingFirebase.encodeString(order.getOrderID())).child("Save Order").setValue(order.getSaveOrder());
                    //Handle change to created order
                    getSupportActionBar().setTitle(R.string.created_order);
                    fragmentMaps.setVisibility(View.GONE);
                    fragmentShipper.setVisibility(View.VISIBLE);
                    fragment = new ListOrderCreatedFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fragmentShopContainer, fragment);
                    transaction.commit();

                }

            });




    }
    public void addUser()
    {
        final CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User user = realm.createObject(User.class,currentUser.getEmail());
                    user.setPhoneNumber("01655713623");
                    user.setFullName(currentUser.getName());
                    user.setAvatar(R.drawable.ic_your_profile);
                    realm.insertOrUpdate(user);
                }
            });
    }


    private User getCurrentUser()
    {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        RealmResults<User> checkUser = realm.where(User.class).equalTo("email",currentUser.getEmail()).findAll();
        if(checkUser.size() == 0)
        {
            addUser();

        }

        User user = realm.where(User.class).equalTo("email",currentUser.getEmail()).findFirst();
        return user;
    }
    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }


    @OnClick(R.id.btnCancelOrder)
    public void cancelOrder()
    {
        btnCreateNewOrder.setVisibility(View.VISIBLE);
        layoutCreateNewOrder.setVisibility(View.GONE);
        layoutControl.setVisibility(View.VISIBLE);

    }
    @OnClick(R.id.btnCreateNewOrder)
    public void createNewOrder()
    {
        btnCreateNewOrder.setVisibility(View.GONE);
        layoutCreateNewOrder.setVisibility(View.VISIBLE);
        layoutControl.setVisibility(View.GONE);
        handleCreateNewOrder();
    }

    @OnItemClick(R.id.listAction)
    public void listViewClicked(AdapterView<?>parent, View view , int position, long id)
    {
        final ListControl listControl = listControls.get(position);
        if (listControl.getIdIcon() == R.drawable.ic_starting_point)
        {
            btnOk.setVisibility(View.VISIBLE);
            btnPlace.setVisibility(View.VISIBLE);
            layoutItem.setVisibility(View.GONE);
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
            layoutItem.setVisibility(View.GONE);
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
        progressDialog = ProgressDialog.show(this ,getResources().getString(R.string.please_wait),
                getResources().getString(R.string.wait_find_direction),true);
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

    private Drawable resizeImage(int resId, int w, int h)
    {
        // load the original Bitmap
        Bitmap BitmapOrg = BitmapFactory.decodeResource(getResources(), resId);
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;
        // calculate the scale
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,width, height, matrix, true);
        return new BitmapDrawable(resizedBitmap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        if(badgerCount >0)
        {
            ActionItemBadge.update(this,menu.findItem(R.id.item_notifycation),resizeImage(R.drawable.ic_notifycation,200,200), ActionItemBadge.BadgeStyles.RED, badgerCount);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_notifycation:
                showNotification();
                //badgerCount++;
                // ActionItemBadge.update(item,badgerCount);
                return true;
            default:  return super.onOptionsItemSelected(item);
        }
    }

    private void showNotification()
    {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final DialogHelpers dialogHelpers = new DialogHelpers();
        dialogHelpers.show(fragmentManager,"New fragment");

    }

}