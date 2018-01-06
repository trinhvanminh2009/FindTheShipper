package com.minh.findtheshipper.Shop;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.Shipper.DetailOrderHistoryActivity;
import com.minh.findtheshipper.common.DialogUserInformation;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.User;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;

public class DetailOrderShopActivity extends AppCompatActivity {
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
    @BindView(R.id.btnCall)
    at.markushi.ui.CircleButton btnCall;
    @BindView(R.id.btnComment)
    at.markushi.ui.CircleButton btnComment;
    @BindView(R.id.txtTime)
    TextView txtTime;
    @BindView(R.id.btnShipperInformation)
    at.markushi.ui.CircleButton btnShipperInformation;
    @BindView(R.id.stateProgressBar)
    StateProgressBar stateProgressBar;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    private Unbinder unbinder;
    private String key = "";
    private String userEmailFromServer = "";
    private OrderTemp order;
    private Realm realm;
    private String currentShipper;
    private int currentStateOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order_shop);
        unbinder = ButterKnife.bind(this);
        initRealm();
        showToolBar();
        changeColorStatusBar();
        loadDataFromServer();
        initSpinner();
        // updateStateProgressBar();

    }

    private void initSpinner() {
        btnUpdate.setVisibility(View.GONE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(DetailOrderShopActivity.this,
                R.array.list_state_order, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /***
         * Spinner start from 0
         * CurrentStateOrder start from 1
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int spinnerPosition = spinner.getSelectedItemPosition();
                SweetAlertDialog sweetAlertDialog;
                if (spinnerPosition != 0) {
                    if (currentShipper != null && !TextUtils.isEmpty(currentShipper.trim())) {
                        if (currentStateOrder == 1) {
                            switch (spinnerPosition) {
                                case 1:
                                    sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                                    sweetAlertDialog.setTitle(R.string.warning);
                                    sweetAlertDialog.setContentText(getString(R.string.are_you_sure_title));
                                    sweetAlertDialog.setCancelText(getString(R.string.cancel));
                                    sweetAlertDialog.show();
                                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                            spinner.setSelection(0);
                                        }
                                    });
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            spinner.setSelection(1);
                                        }
                                    });
                                    break;


                                case 2:
                                case 3:
                                    sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                                    sweetAlertDialog.setTitle(R.string.warning);
                                    sweetAlertDialog.setContentText(getString(R.string.warning_order_1));
                                    sweetAlertDialog.show();
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                        }
                                    });
                                    spinner.setSelection(0);
                                    break;

                            }
                        }
                        if (currentStateOrder == 2) {
                            switch (spinnerPosition) {
                                case 2:
                                case 3:
                                    sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                                    sweetAlertDialog.setTitle(R.string.warning);
                                    sweetAlertDialog.setContentText(getString(R.string.warning_order_2));
                                    sweetAlertDialog.show();
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                        }
                                    });
                                    spinner.setSelection(1);
                                    break;
                            }
                        }
                        if (currentStateOrder == 3) {
                            switch (spinnerPosition) {
                                case 1:
                                    sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                                    sweetAlertDialog.setTitle(R.string.warning);
                                    sweetAlertDialog.setContentText(getString(R.string.warning_order_2));
                                    sweetAlertDialog.show();
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                        }
                                    });
                                    spinner.setSelection(2);
                                    break;
                                case 2:
                                    sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                                    sweetAlertDialog.setTitle(R.string.warning);
                                    sweetAlertDialog.setContentText(getString(R.string.are_you_sure_title));
                                    sweetAlertDialog.setCancelText(getString(R.string.cancel));
                                    sweetAlertDialog.show();
                                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                            spinner.setSelection(2);
                                        }
                                    });
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            spinner.setSelection(1);
                                        }
                                    });
                                    break;
                                case 3:
                                    sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                                    sweetAlertDialog.setTitle(R.string.warning);
                                    sweetAlertDialog.setContentText(getString(R.string.are_you_sure_title));
                                    sweetAlertDialog.setCancelText(getString(R.string.cancel));
                                    sweetAlertDialog.show();
                                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                            spinner.setSelection(2);
                                        }
                                    });
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            spinner.setSelection(3);
                                        }
                                    });
                                    break;
                            }
                        }
                        if (currentStateOrder == 4) {
                            switch (spinnerPosition) {
                                case 1:
                                case 2:
                                    sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                                    sweetAlertDialog.setTitle(R.string.warning);
                                    sweetAlertDialog.setContentText(getString(R.string.warning_order_4));
                                    sweetAlertDialog.show();
                                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                        }
                                    });
                                    spinner.setSelection(3);
                                    break;
                            }

                        }
                    } else {
                        sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                        sweetAlertDialog.setTitle(R.string.warning);
                        sweetAlertDialog.setContentText(getString(R.string.have_not_shipper));
                        sweetAlertDialog.show();
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                        spinner.setSelection(0);
                    }
                } else {
                    if (currentStateOrder > 1) {
                        sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this, SweetAlertDialog.WARNING_TYPE);
                        sweetAlertDialog.setTitle(R.string.warning);
                        sweetAlertDialog.setContentText(getString(R.string.warning_delete_shipper));
                        sweetAlertDialog.setCancelText(getString(R.string.cancel));
                        sweetAlertDialog.show();
                        //Do not thing
                        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                spinner.setSelection(currentStateOrder - 1);
                            }
                        });
                        //Remove shipper, back order to step 1
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                if (userEmailFromServer != null && getCurrentUser().getEmail() != null) {
                                    if (!(DetailOrderShopActivity.this).isFinishing()) { //Make sure activity is running

                                        final DatabaseReference orderDataBase = FirebaseDatabase.getInstance().getReference("order");
                                        orderDataBase.child(key).child("Shipper").removeValue();
                                        orderDataBase.child(key).child("State Order").setValue(1);
                                        spinner.setSelection(0);
                                        sweetAlertDialog.dismissWithAnimation();
                                    }
                                }
                            }
                        });
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                TastyToast.makeText(DetailOrderShopActivity.this, "not Changed", TastyToast.LENGTH_SHORT, TastyToast.CONFUSING);

            }
        });
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

    private void changeColorStatusBar() {
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.btnShipperInformation})
    public void evenClick(final View view) {
        switch (view.getId()) {
            case R.id.btnShipperInformation:
                showDialogShipperInformation();
                break;
        }
    }

    private void showDialogShipperInformation() {
        Bundle bundle = new Bundle();
        if (currentShipper != null && !currentShipper.equals("")) {
            bundle.putString("userEmail", currentShipper);
            FragmentManager fragmentManager = getSupportFragmentManager();
            final DialogUserInformation dialogHelpers = new DialogUserInformation();
            dialogHelpers.show(fragmentManager, "New fragment");
            dialogHelpers.setArguments(bundle);
        } else {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this);
            sweetAlertDialog.setTitle(R.string.warning);
            sweetAlertDialog.setContentText(getString(R.string.shipper_not_found));
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            });
            sweetAlertDialog.show();
        }

    }

    private void loadDataFromServer() {
        order = new OrderTemp();
        key = getIntent().getStringExtra("orderKey");
        Toast.makeText(DetailOrderShopActivity.this, key, Toast.LENGTH_SHORT).show();
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
                String currentState = dataSnapshot.child(orderKey).child("State Order").getValue(String.class);
                String deliveryTime = dataSnapshot.child(orderKey).child("Delivery Time").getValue(String.class);
                String shortStartPlace = EncodingFirebase.getShortAddress(startPlace);
                String shortFinishPlace = EncodingFirebase.getShortAddress(finishPlace);
                currentShipper = dataSnapshot.child(orderKey).child("Shipper").getValue(String.class);

                order.setOrderID(orderKey);
                if (status != null) {
                    order.setStatus(status);
                }
                if (startPlace != null) {
                    order.setStartPoint(startPlace);
                }
                if (finishPlace != null) {
                    order.setFinishPoint(finishPlace);

                }
                if (advancedMoney != null && txtAdvancedMoney != null) {
                    order.setAdvancedMoney(advancedMoney);
                    txtAdvancedMoney.setText(advancedMoney);
                }
                if (phoneNumber != null && txtPhoneNumber != null) {
                    order.setPhoneNumber(phoneNumber);
                    txtPhoneNumber.setText(phoneNumber);
                }
                if (shipMoney != null && txtPrice != null) {
                    order.setShipMoney(shipMoney);
                    txtPrice.setText(shipMoney);

                }
                if (distance != null && txtDistance != null) {
                    order.setDistance(distance);
                    txtDistance.setText(distance);
                }
                if (dateTime != null && txtTimeAgo != null) {
                    order.setDateTime(dateTime);
                    txtTimeAgo.setText(TimeAgoHelpers.getTimeAgo(dateTime, DetailOrderShopActivity.this));
                }

                if (deliveryTime != null && txtTime != null) {
                    order.setDeliveryTime(deliveryTime);
                    txtTime.setText(deliveryTime);
                }

                order.setSavedOrder(saveOrder);
                if (shortStartPlace != null && txtStartPlace != null) {
                    txtStartPlace.setText(shortStartPlace);

                }
                if (shortFinishPlace != null && txtFinishPlace != null) {
                    txtFinishPlace.setText(shortFinishPlace);
                }
                if (note != null && txtNote != null) {
                    order.setNote(note);
                    txtNote.setText(note);
                }
                if (currentState != null) {
                    currentStateOrder = Integer.parseInt(currentState);
                    String[] descriptionData = {"Find", "Confirm", "Delivery", "Done"};
                    if (stateProgressBar != null) {
                        stateProgressBar.setStateDescriptionData(descriptionData);
                        if (currentState.equals("1")) {
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
                            spinner.setSelection(0, true);

                        }
                        if (currentState.equals("2")) {
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                            spinner.setSelection(1, true);
                        }
                        if (currentState.equals("3")) {
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
                            spinner.setSelection(2, true);
                        }
                        if (currentState.equals("4")) {
                            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
                            spinner.setSelection(3, true);
                        }
                        stateProgressBar.setAnimationDuration(3000);
                        stateProgressBar.enableAnimationToCurrentState(true);
                    }

                }



              /*  SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(DetailOrderShopActivity.this,
                        SweetAlertDialog.CUSTOM_IMAGE_TYPE);
                sweetAlertDialog.setTitle(getString(R.string.ask_for_accept_shipper_title));
                sweetAlertDialog.setContentText(getString(R.string.ask_for_accept_shipper));
                sweetAlertDialog.setCustomImage(R.drawable.ic_ask_question);
                sweetAlertDialog.show();
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });*/
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
                if (nameCreator != null && url != null) {
                    txtUserName.setText(nameCreator);
                    Glide.with(DetailOrderShopActivity.this).load(EncodingFirebase.decodeString(url))
                            .apply(RequestOptions.circleCropTransform()).thumbnail(0.7f).into(imgUserImage);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void initRealm() {
        Realm.init(this);
        realm = null;
        realm = Realm.getDefaultInstance();
    }

}
