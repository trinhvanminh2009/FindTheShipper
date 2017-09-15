package com.minh.findtheshipper.Shipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.OrderTemp;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by trinh on 9/14/2017.
 */

public class DetailOrderShipperFragment extends android.support.v4.app.Fragment {
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
    private Unbinder unbinder;
    private String []key = new String[2];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            key = bundle.getStringArray("orderKey");
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final DatabaseReference orderDataBase = FirebaseDatabase.getInstance().getReference("order");
        final DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("user");
        final EncodingFirebase encodingFirebase = new EncodingFirebase();
        orderDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final String orderKey = key[1];
                TimeAgoHelpers timeAgoHelpers = new TimeAgoHelpers();
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
                txtStartPlace.setText(startPlace);
                txtFinishPlace.setText(finishPlace);
                txtAdvancedMoney.setText(advancedMoney);
                txtPhoneNumber.setText(phoneNumber);
                txtPrice.setText(shipMoney);
                txtNote.setText(note);
                txtDistance.setText(distance);
                txtTimeAgo.setText(timeAgoHelpers.getTimeAgo(dateTime, getContext()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        userDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String  userKey = encodingFirebase.getEmailFromUserID(key[1]);
                String nameCreator = dataSnapshot.child(userKey).child("Name").getValue(String.class);
                String url = dataSnapshot.child(userKey).child("Avatar").getValue(String.class);
                txtUserName.setText(nameCreator);
                Glide.with(getContext()).load(encodingFirebase.decodeString(url)).apply(RequestOptions.circleCropTransform()).thumbnail(0.7f).into(imgUserImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        TastyToast.makeText(getContext(), key[0]+ " "+ key[1], TastyToast.LENGTH_SHORT, TastyToast.INFO);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
