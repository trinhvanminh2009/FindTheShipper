package com.minh.findtheshipper.Shipper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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
import com.minh.findtheshipper.helpers.CommentDialogHelpers;
import com.minh.findtheshipper.helpers.DirectionHelpers;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.OneSignalHelpers;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.helpers.listeners.DirectionFinderListeners;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.Order;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.Route;
import com.minh.findtheshipper.models.RealmObject.User;
import com.minh.findtheshipper.utils.AnimationUtils;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmList;

public class DetailOrderShipperActivity extends AppCompatActivity implements OnMapReadyCallback,
        DirectionFinderListeners {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.imgUserImage)
    ImageView imgUserImage;
    @BindView(R.id.txtUserName)
    TextView txtUserName;
    @BindView(R.id.txtTimeAgo)
    TextView txtTimeAgo;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.txtStartPlace)
    TextView txtStartPlace;
    @BindView(R.id.txtFinishPlace)
    TextView txtFinishPlace;
    @BindView(R.id.txtPhoneNumber)
    TextView txtPhoneNumber;
    @BindView(R.id.txtNote)
    TextView txtNote;
    @BindView(R.id.txtAdvancedMoney)
    TextView txtAdvancedMoney;
    @BindView(R.id.txtDistance)
    TextView txtDistance;
    @BindView(R.id.btnGetOrder)
    Button btnGetOrder;
    @BindView(R.id.btnCall)
    at.markushi.ui.CircleButton btnCall;
    @BindView(R.id.btnComment)
    at.markushi.ui.CircleButton btnComment;
    @BindView(R.id.txtTime)
    TextView txtTime;
    @BindView(R.id.btnSave)
    at.markushi.ui.CircleButton btnSave;
    @BindView(R.id.btnDelete)
    at.markushi.ui.CircleButton btnDelete;
    @BindView(R.id.tvSaveOrDelete)
    TextView tvSaveOrDelete;
    private Unbinder unbinder;
    private String key = "";
    private String userEmailFromServer = "";
    private List<Marker> startMarkers = new ArrayList<>();
    private List<Marker> finishMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private OrderTemp order;
    private Realm realm;
    private GoogleMap mMap;
    private String[] orderKey = new String[2];
    private boolean dataAddedOrDidNotAdd = false;
    private boolean isClickGetOrder = false;
    // This variable to know data added on server or didn't
    //Because we check on data change, after add delivery man
    //Data also change. We have to check that!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_shipper);
        unbinder = ButterKnife.bind(this);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        showToolBar();
        changeColorStatusBar();
        initRealm();
        loadDataFromServer();
        showSaveOrDeleteButton();
    }

    private void showSaveOrDeleteButton() {
        if (orderKey[1].equals("History")) {
            btnDelete.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.GONE);
            tvSaveOrDelete.setText(getString(R.string.delete));
        }
        if (orderKey[1].equals("Present")) {
            btnDelete.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);
            tvSaveOrDelete.setText(getString(R.string.save));
        }
    }


    public void initRealm() {
        Realm.init(this);
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @OnClick({R.id.btnGetOrder, R.id.btnCall, R.id.btnComment, R.id.btnSave, R.id.btnDelete})
    public void eventClick(final View v) {
        SweetAlertDialog sweetAlertDialog;
        switch (v.getId()) {
            case R.id.btnGetOrder:
                //Not allow take their own order

                isClickGetOrder = true;
                if (userEmailFromServer != null && getCurrentUser().getEmail() != null) {
                    if (userEmailFromServer.equals(getCurrentUser().getEmail())) {
                        if (!(DetailOrderShipperActivity.this).isFinishing()) { //Make sure activity is running
                            sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                            sweetAlertDialog.setTitleText(getString(R.string.warning));
                            sweetAlertDialog.setContentText(getString(R.string.status_take_your_order_content));
                            sweetAlertDialog.setConfirmText(getString(R.string.ok));
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            });
                            sweetAlertDialog.show();
                        }

                    } else {
                        final DatabaseReference orderDataBase = FirebaseDatabase.getInstance().getReference("order");

                        orderDataBase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(final DataSnapshot dataSnapshot) {
                                //Available shipper took this orders
                                String currentShipper = dataSnapshot.child(key).child("Shipper").getValue(String.class);
                                if (isClickGetOrder) {
                                    if (currentShipper != null) {
                                        if (!dataAddedOrDidNotAdd) {
                                            if (!(DetailOrderShipperActivity.this).isFinishing()) {
                                                SweetAlertDialog sweetAlertDialog;
                                                sweetAlertDialog = new SweetAlertDialog(DetailOrderShipperActivity.this, SweetAlertDialog.WARNING_TYPE);
                                                sweetAlertDialog.setTitleText(getString(R.string.warning));
                                                if (currentShipper.equals(EncodingFirebase.encodeString(getCurrentUser().getEmail()))) {
                                                    sweetAlertDialog.setContentText(getString(R.string.status_already_took_orders));
                                                } else {
                                                    sweetAlertDialog.setContentText(getString(R.string.status_take_order_already_ordered));
                                                }
                                                sweetAlertDialog.setConfirmText(getString(R.string.ok));
                                                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        sweetAlertDialog.dismissWithAnimation();
                                                    }
                                                });
                                                sweetAlertDialog.show();
                                            }
                                        }
                                        dataAddedOrDidNotAdd = false;

                                    }
                                    if (currentShipper == null) {

                                        if (!(DetailOrderShipperActivity.this).isFinishing()) {

                                            SweetAlertDialog sweetAlertDialog;
                                            sweetAlertDialog = new SweetAlertDialog(DetailOrderShipperActivity.this, SweetAlertDialog.WARNING_TYPE);
                                            sweetAlertDialog.setTitleText(getString(R.string.status_want_to_take_order_title));
                                            sweetAlertDialog.setContentText(getString(R.string.status_want_to_take_order));
                                            sweetAlertDialog.setConfirmText(getString(R.string.ok));
                                            sweetAlertDialog.setCancelText(getString(R.string.cancel));
                                            sweetAlertDialog.show();
                                            sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    sweetAlertDialog.dismissWithAnimation();
                                                }
                                            });
                                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                    sweetAlertDialog.dismissWithAnimation();
                                                    orderDataBase.child(key).child("Shipper").setValue(EncodingFirebase.encodeString(getCurrentUser().getEmail()));
                                                    orderDataBase.child(key).child("State Order").setValue("1");
                                                    String message = getCurrentUser().getFullName() + " " + getString(R.string.notification_state_2);
                                                    OneSignalHelpers.sendNotification(EncodingFirebase.decodeString(EncodingFirebase.getEmailFromUserID(key)),
                                                            getCurrentUser().getEmail(), message);
                                                    String title = "Get order";
                                                    String keyNotification = key + "_notification_" + getCoutnNotification(key);
                                                    orderDataBase.child(key).child("Notifications").child(keyNotification).child("From")
                                                            .setValue(EncodingFirebase.encodeString(getCurrentUser().getEmail()));
                                                    DateFormat datetimeFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
                                                    String date = datetimeFormat.format(Calendar.getInstance().getTime());
                                                    orderDataBase.child(key).child("Notifications").child(keyNotification).child("Datetime").setValue(date);
                                                    orderDataBase.child(key).child("Notifications").child(keyNotification).child("Message").setValue(message);
                                                    orderDataBase.child(key).child("Notifications").child(keyNotification).child("Title").setValue(title);
                                                    orderDataBase.child(key).child("Notifications").child(keyNotification).child("Show Again").setValue(true);


                                                        SweetAlertDialog dialogSuccess = new SweetAlertDialog(DetailOrderShipperActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                                        dialogSuccess.setTitleText(getString(R.string.success));
                                                        dialogSuccess.setContentText(getString(R.string.status_took_order));
                                                        dialogSuccess.show();

                                                }
                                            });

                                            dataAddedOrDidNotAdd = true;
                                            isClickGetOrder = false;

                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

                break;
            case R.id.btnSave:
                final Order orderRealm = realm.where(Order.class).equalTo("orderID", order.getOrderID()).findFirst();
                if (orderRealm == null)//Check it available in database
                {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            final Order orderToRealm = realm.createObject(Order.class, order.getOrderID());
                            orderToRealm.setStatus(order.getStatus());
                            orderToRealm.setStartPoint(order.getStartPoint());
                            orderToRealm.setFinishPoint(order.getFinishPoint());
                            orderToRealm.setAdvancedMoney(order.getAdvancedMoney());
                            orderToRealm.setShipMoney(order.getShipMoney());
                            orderToRealm.setNote(order.getNote());
                            orderToRealm.setSaveOrder(order.getSavedOrder());
                            orderToRealm.setPhoneNumber(order.getPhoneNumber());
                            orderToRealm.setDistance(order.getDistance());
                            realm.insertOrUpdate(orderToRealm);
                            getCurrentUser().getOrderListSave().add(orderToRealm);
                            realm.insertOrUpdate(getCurrentUser());
                            if (!orderToRealm.getSaveOrder()) //Check order is false exists before handle
                            {
                                AnimationUtils.animateItemView(v);
                                //Check it already exists in list order save of user
                                boolean checkAlready = false;
                                User user = getCurrentUser();
                                RealmList<Order> orders = user.getOrderListSave();
                                for (int i = 0; i < orders.size(); i++) {
                                    if (orders.get(i).getOrderID().equals(orderToRealm.getOrderID())) {
                                        checkAlready = true;
                                    }
                                }
                                if (!checkAlready) {
                                    orderToRealm.setSaveOrder(true);
                                    realm.insertOrUpdate(orderToRealm);
                                    user.getOrderListSave().add(orderToRealm);
                                    realm.insertOrUpdate(user);
                                    AnimationUtils.animateItemView(v);
                                    TastyToast.makeText(v.getContext(), v.getResources().getString(R.string.save_order), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);

                                }
                                if (checkAlready) {
                                    orderToRealm.setSaveOrder(true);
                                    realm.insertOrUpdate(orderToRealm);
                                    TastyToast.makeText(v.getContext(), v.getResources().getString(R.string.save_order), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);

                                }
                            } else {
                                AnimationUtils.animateItemView(v);
                                TastyToast.makeText(v.getContext(), v.getResources().getString(R.string.save_order_exists), TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                            }
                        }
                    });

                } else {
                    if (!orderRealm.getSaveOrder()) //Check order is false exists before handle
                    {
                        AnimationUtils.animateItemView(v);
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                //Check it already exists in list order save of user
                                boolean checkAlready = false;
                                User user = getCurrentUser();
                                RealmList<Order> orders = user.getOrderListSave();
                                for (int i = 0; i < orders.size(); i++) {
                                    if (orders.get(i).getOrderID().equals(order.getOrderID())) {
                                        checkAlready = true;
                                    }
                                }
                                if (!checkAlready) {
                                    //Set it again, to make sure it not null
                                    orderRealm.setSaveOrder(true);
                                    orderRealm.setShipMoney(order.getShipMoney());
                                    orderRealm.setDistance(order.getDistance());
                                    realm.insertOrUpdate(orderRealm);
                                    user.getOrderListSave().add(orderRealm);
                                    realm.insertOrUpdate(user);
                                    AnimationUtils.animateItemView(v);
                                    TastyToast.makeText(v.getContext(), v.getResources().getString(R.string.save_order), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);

                                }
                                if (checkAlready) {
                                    //Set it again, to make sure it not null
                                    orderRealm.setSaveOrder(true);
                                    orderRealm.setShipMoney(order.getShipMoney());
                                    orderRealm.setDistance(order.getDistance());
                                    realm.insertOrUpdate(orderRealm);
                                    TastyToast.makeText(v.getContext(), v.getResources().getString(R.string.save_order), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                                }
                            }
                        });
                    } else {
                        AnimationUtils.animateItemView(v);
                        TastyToast.makeText(v.getContext(), v.getResources().getString(R.string.save_order_exists), TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                    }
                }
                break;

            case R.id.btnCall:
                String tempPhone = order.getPhoneNumber().replaceAll("[^0-9]+", " ");
                List<String> phoneNumber = Arrays.asList(tempPhone.trim().split(" "));
                Intent intentPhone = new Intent(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel:" + phoneNumber.get(0)));
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                v.getContext().startActivity(intentPhone);
                break;
            case R.id.btnComment:
                Bundle bundle = new Bundle();
                bundle.putString("orderID", order.getOrderID());
                FragmentManager fragmentManager = getSupportFragmentManager();
                final CommentDialogHelpers dialogHelpers = new CommentDialogHelpers();
                dialogHelpers.show(fragmentManager, "New fragment");
                dialogHelpers.setArguments(bundle);
                break;
            case R.id.btnDelete:
                sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
                sweetAlertDialog.setTitleText(getString(R.string.are_you_sure_title));
                sweetAlertDialog.setContentText(getString(R.string.are_you_sure_content));
                sweetAlertDialog.setConfirmText(getString(R.string.ok));
                sweetAlertDialog.setCancelText(getString(R.string.cancel));
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });

                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                final Order orderRealm = realm.where(Order.class).equalTo("orderID", order.getOrderID()).findFirst();
                                orderRealm.setSaveOrder(false);
                                User user = getCurrentUser();
                                user.getOrderListSave().remove(orderRealm);
                                realm.insertOrUpdate(user);
                                onBackPressed();

                            }
                        });
                    }
                });
                sweetAlertDialog.show();
                finish();
                break;
        }

    }

    private long getCoutnNotification(final String key) {
        final long[] result = {0};
        final DatabaseReference databaseNotification = FirebaseDatabase.getInstance().getReference("order/" + key + "/Notifications");
        databaseNotification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                result[0] = dataSnapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return result[0];
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

    private void showToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void changeColorStatusBar() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

    }

    private void loadDataFromServer() {
        order = new OrderTemp();
        orderKey = getIntent().getStringArrayExtra("orderKey");
        key = orderKey[0];
        final DatabaseReference orderDataBase = FirebaseDatabase.getInstance().getReference("order");
        final DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("user");

        orderDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String orderKey = key;
                userEmailFromServer = EncodingFirebase.convertToRightEmail(key);
                String status = dataSnapshot.child(orderKey).child("Status").getValue(String.class);
                String startPlace = dataSnapshot.child(orderKey).child("Start place").getValue(String.class);
                String finishPlace = dataSnapshot.child(orderKey).child("Finish place").getValue(String.class);
                String advancedMoney = dataSnapshot.child(orderKey).child("Advanced money").getValue(String.class);
                String phoneNumber = dataSnapshot.child(orderKey).child("Phone number").getValue(String.class);
                String shipMoney = dataSnapshot.child(orderKey).child("Ship Money").getValue(String.class);
                String note = dataSnapshot.child(orderKey).child("Note").getValue(String.class);
                String distance = dataSnapshot.child(orderKey).child("Distance").getValue(String.class);
                String dateTime = dataSnapshot.child(orderKey).child("Datetime").getValue(String.class);
                Boolean saveOrder = dataSnapshot.child(orderKey).child("Save Order").getValue(Boolean.class);
                String shortStartPlace = EncodingFirebase.getShortAddress(startPlace);
                String shortFinishPlace = EncodingFirebase.getShortAddress(finishPlace);

                order.setOrderID(orderKey);
                order.setStatus(status);
                order.setStartPoint(startPlace);
                order.setFinishPoint(finishPlace);
                order.setAdvancedMoney(advancedMoney);
                order.setShipMoney(shipMoney);
                order.setPhoneNumber(phoneNumber);
                order.setDistance(distance);
                order.setDateTime(dateTime);
                order.setSavedOrder(saveOrder);
                if (txtStartPlace != null && txtFinishPlace != null && txtAdvancedMoney != null
                        && txtPrice != null && txtNote != null && txtDistance != null && txtTimeAgo != null) {
                    txtStartPlace.setText(shortStartPlace);
                    txtFinishPlace.setText(shortFinishPlace);
                    txtAdvancedMoney.setText(advancedMoney);
                    txtPhoneNumber.setText(phoneNumber);
                    txtPrice.setText(shipMoney);
                    txtNote.setText(note);
                    txtDistance.setText(distance);
                    txtTimeAgo.setText(TimeAgoHelpers.getTimeAgo(dateTime, DetailOrderShipperActivity.this));
                    showPathOnMap(startPlace, finishPlace);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userKey = EncodingFirebase.getEmailFromUserID(key);
                String nameCreator = dataSnapshot.child(userKey).child("Name").getValue(String.class);
                String url = dataSnapshot.child(userKey).child("Avatar").getValue(String.class);
                if (nameCreator != null && url != null && txtUserName != null && imgUserImage != null) {
                    txtUserName.setText(nameCreator);
                    Glide.with(DetailOrderShipperActivity.this).load(EncodingFirebase.decodeString(url))
                            .apply(RequestOptions.circleCropTransform()).thumbnail(0.7f).into(imgUserImage);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void showPathOnMap(String startPlace, String finishPlace) {
        try {
            new DirectionHelpers(this, startPlace, finishPlace).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng HCMCity = new LatLng(10.766333, 106.694036);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HCMCity, 18));
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
        if (mMap.isMyLocationEnabled()) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                final double[] longitude = {location.getLongitude()};
                final double[] latitude = {location.getLatitude()};
                android.location.LocationListener locationListener = new android.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        longitude[0] = location.getLongitude();
                        latitude[0] = location.getLatitude();
                        String currentAddress = EncodingFirebase.getCompleteAddressString(
                                DetailOrderShipperActivity.this, latitude[0], longitude[0]);
                        Log.d("Current Address", currentAddress);


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
                };
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long) 2000, (float) 10, locationListener);
            }


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
            LatLngBounds bounds = builder.build();
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));
            //End Zoom path fit with maps
            if (txtDistance != null && txtTime != null) {
                txtDistance.setText(route.distance.text);
                txtTime.setText(route.duration.text);
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

}
