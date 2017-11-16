package com.minh.findtheshipper.Shipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.SortOrderTempHelpers;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListViewHistoryOrderItem;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.RealmObject.User;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;

public class ListOrderHistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private Realm realm;
    private RecyclerView.Adapter adapter;
    private ArrayList<OrderTemp> orderList;
    RecyclerView.LayoutManager layoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recycle_view, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Realm.init(getActivity());
        initRealm();
        loadAllList();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadAllList();
        Log.e("Error", "Show right here");

    }

    public void loadAllList() {
        try {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
            Query query = mDatabase.orderByChild("Datetime");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    orderList = new ArrayList<>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        final String key = child.getKey();
                        String currentShipper = dataSnapshot.child(key).child("Shipper").getValue(String.class);
                        if (currentShipper != null) {
                            if (currentShipper.equals(EncodingFirebase.encodeString(getCurrentUser().getEmail()))) {
                                String status = dataSnapshot.child(key).child("Status").getValue(String.class);
                                String startPlace = dataSnapshot.child(key).child("Start place").getValue(String.class);
                                String finishPlace = dataSnapshot.child(key).child("Finish place").getValue(String.class);
                                String advancedMoney = dataSnapshot.child(key).child("Advanced money").getValue(String.class);
                                String phoneNumber = dataSnapshot.child(key).child("Phone number").getValue(String.class);
                                String shipMoney = dataSnapshot.child(key).child("Ship Money").getValue(String.class);
                                String note = dataSnapshot.child(key).child("Note").getValue(String.class);
                                String distance = dataSnapshot.child(key).child("Distance").getValue(String.class);
                                String dateTime = dataSnapshot.child(key).child("Datetime").getValue(String.class);
                                Boolean saveOrder = dataSnapshot.child(key).child("Save Order").getValue(Boolean.class);
                                OrderTemp orderTemp = new OrderTemp();
                                orderTemp.setOrderID(key);
                                orderTemp.setStatus(status);
                                orderTemp.setStartPoint(startPlace);
                                orderTemp.setFinishPoint(finishPlace);
                                orderTemp.setAdvancedMoney(advancedMoney);
                                orderTemp.setPhoneNumber(phoneNumber);
                                orderTemp.setShipMoney(shipMoney);
                                orderTemp.setNote(note);
                                orderTemp.setDistance(distance);
                                orderTemp.setDateTime(dateTime);
                                orderTemp.setSavedOrder(saveOrder);
                                orderList.add(orderTemp);
                            }

                        }

                    }
                    try {
                        Collections.sort(orderList, new SortOrderTempHelpers());
                        adapter = new CustomAdapterListViewHistoryOrderItem(orderList, getContext());
                        recyclerView.setAdapter(adapter);
                    } catch (Exception e) {
                        Log.e("Error", "Error in loadAllList of ListOrderShipperFragmentCommon");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } catch (Exception e) {
            Log.e("Error", "Error in loadAllList of ListOrderShipperFragmentCommon");
        }


    }

    public void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }
}
