package com.minh.findtheshipper.helpers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListViewNotification;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.NotificationObject;
import com.minh.findtheshipper.models.User;

import java.util.ArrayList;

import io.realm.Realm;


public class DialogHelpers extends DialogFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<NotificationObject> notificationObjectArrayList;
    private Realm realm;
    RecyclerView.LayoutManager layoutManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("ConstantConditions")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().getAttributes().windowAnimations = R.style.MyDialogAnimation;
        View view = inflater.inflate(R.layout.list_recycle_view, container, false);
        initRealm();
        recyclerView = view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadNotification();
        return view;
    }

    public void initRealm() {
        Realm.init(getActivity());
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    /***
     * Get all notification from FireBase database
     * Step 1: Get all key order, because notifications are child of orders by loop
     * Step 2: Check order must be this user created
     * Step 3: Check order available Notifications
     * Step 4: Get all notifications with value "Show Again" is true by loop
     * Step 5: Get all necessary information add into list notifications
     */
    public void loadNotification() {
        final DatabaseReference dataNotification = FirebaseDatabase.getInstance().getReference();
        dataNotification.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notificationObjectArrayList = new ArrayList<>();
                for (DataSnapshot order : dataSnapshot.getChildren()) {
                    String key = order.getKey();
                    //Get key from Realm- object and compare with key from server
                    //To make sure this notification only for this user
                    if (EncodingFirebase.encodeString(getCurrentUser().getEmail()).
                            equals(EncodingFirebase.getEmailFromUserID(key))) {
                        if (dataSnapshot.child(key).child("Notifications").exists()) {
                            //Query into notifications. This step
                            DatabaseReference childNotification = FirebaseDatabase.getInstance().getReference();
                            childNotification.child("order").child(key).child("Notifications").addValueEventListener(new ValueEventListener() {
                                @SuppressWarnings("ConstantConditions")
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot notification : dataSnapshot.getChildren()) {
                                        for (int i = 0; i < (int) dataSnapshot.getChildrenCount(); i++) {
                                            String keyNotification = notification.getKey();
                                            if (dataSnapshot.child(keyNotification).child("Show Again").getValue(Boolean.class)) {
                                                String title = dataSnapshot.child(keyNotification).child("Title").getValue(String.class);
                                                String message = dataSnapshot.child(keyNotification).child("Message").getValue(String.class);
                                                String dateTime = dataSnapshot.child(keyNotification).child("Datetime").getValue(String.class);
                                                NotificationObject notificationObject =
                                                        new NotificationObject(R.mipmap.ic_launcher_app, message, title, dateTime, true);
                                                if (notificationObject != null) {
                                                    notificationObjectArrayList.add(notificationObject);

                                                }
                                            }
                                        }
                                    }
                                    adapter = new CustomAdapterListViewNotification(getContext(), notificationObjectArrayList);
                                    recyclerView.setAdapter(adapter);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
