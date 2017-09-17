package com.minh.findtheshipper.Shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.helpers.EncodingFireBase;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.SortOrderTempHelpers;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrder;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListOrderCreatedFragment extends android.support.v4.app.Fragment {

    private Realm realm;
    private ArrayList<OrderTemp> orderList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Realm.init(getActivity());
        initRealm();
        loadAllList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recycle_view, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);

        return view;
    }

    public void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    public void loadAllList() {
        try {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
            Query query = mDatabase.orderByChild("Datetime");
            final List<String> listKeyFromFireBase = new ArrayList<>();
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Add keys into list template
                    orderList = new ArrayList<>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        final String key = child.getKey();
                        listKeyFromFireBase.add(key);

                    }
                    //Check key and then get keys are created by current user
                    String key = null;
                    for (int j = 0; j < checkKey(listKeyFromFireBase).size(); j++) {
                        key = checkKey(listKeyFromFireBase).get(j);
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
                        String userGetOrder = dataSnapshot.child(key).child("User Get Order").getValue(String.class);
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
                        orderTemp.setUserGetOrder(userGetOrder);
                        orderList.add(orderTemp);
                    }
                    try {
                        Collections.sort(orderList, new SortOrderTempHelpers());
                        adapter = new CustomAdapterListviewOrder(getActivity(), orderList);
                        recyclerView.setAdapter(adapter);

                    } catch (Exception e) {
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } catch (Exception e) {
        }
    }

    /**
     * Check key from server created by current user
     */
    private List<String> checkKey(List<String> keyServer) {
        List<String> result = new ArrayList<>();
        EncodingFireBase encodingFireBase = new EncodingFireBase();
        for (int i = 0; i < keyServer.size(); i++) {

            if (keyServer.get(i).contains(encodingFireBase.encodeString(getCurrentUser().getEmail()))) {
                result.add(keyServer.get(i));
            }

        }
        return result;
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        User user = realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
        return user;
    }

    public void deleteAll() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<Order> results = realm.where(Order.class).findAll();
                results.deleteAllFromRealm();
            }
        });

    }

}
