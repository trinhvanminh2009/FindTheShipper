package com.minh.findtheshipper.Shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.SortOrderTempHelpers;
import com.minh.findtheshipper.models.Adapters.ShopAdapters.CustomAdapterListviewOrder;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.User;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;

public class ListOrderCreatedFragment extends android.support.v4.app.Fragment {

    private Realm realm;
    private ArrayList<OrderTemp> orderList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  SweetAlertDialog sweetAlertDialog;
    private String TAG = "ListOrder";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            RecyclerView.LayoutManager layoutManager;
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            sweetAlertDialog= new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.setTitle(R.string.LOADING);
            sweetAlertDialog.show();
            Realm.init(getActivity());
            initRealm();
        }catch (Exception e){
            Log.e(TAG, "onViewCreated: "+e.toString() );
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recycle_view, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        return view;
    }

    public void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }


    @Override
    public void onResume() {
        super.onResume();

        loadAllList();
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
            orderList = new ArrayList<>();
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    getAllData(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateData(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    deleteItemData(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            sweetAlertDialog.dismissWithAnimation();
        } catch (Exception e) {
            Log.e("Error", "In loadAllList in ListOrderCreatedFragment");
        }
    }

    private void getAllData(DataSnapshot dataSnapshot) {

        String key = dataSnapshot.getKey();
        String status = dataSnapshot.child("Status").getValue(String.class);
        String startPlace = dataSnapshot.child("Start place").getValue(String.class);
        String finishPlace = dataSnapshot.child("Finish place").getValue(String.class);
        String advancedMoney = dataSnapshot.child("Advanced money").getValue(String.class);
        String phoneNumber = dataSnapshot.child("Phone number").getValue(String.class);
        String shipMoney = dataSnapshot.child("Ship Money").getValue(String.class);
        String note = dataSnapshot.child("Note").getValue(String.class);
        String distance = dataSnapshot.child("Distance").getValue(String.class);
        String dateTime = dataSnapshot.child("Datetime").getValue(String.class);
        Boolean saveOrder = dataSnapshot.child("Save Order").getValue(Boolean.class);
        String userGetOrder = dataSnapshot.child("Shipper").getValue(String.class);
        Boolean showAgain = dataSnapshot.child("Show Again").getValue(Boolean.class);
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
        if(userGetOrder != null){
            orderTemp.setUserGetOrder(userGetOrder);
        }

        if (showAgain != null) {
            if (showAgain) {
                if (checkKey(key)) {
                    orderList.add(orderTemp);
                }
            }
        }
        Collections.sort(orderList, new SortOrderTempHelpers());
        sweetAlertDialog.dismissWithAnimation();
        adapter = new CustomAdapterListviewOrder(getActivity(), orderList);
        recyclerView.setAdapter(adapter);
    }

    private void updateData(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        String status = dataSnapshot.child("Status").getValue(String.class);
        String startPlace = dataSnapshot.child("Start place").getValue(String.class);
        String finishPlace = dataSnapshot.child("Finish place").getValue(String.class);
        String advancedMoney = dataSnapshot.child("Advanced money").getValue(String.class);
        String phoneNumber = dataSnapshot.child("Phone number").getValue(String.class);
        String shipMoney = dataSnapshot.child("Ship Money").getValue(String.class);
        String note = dataSnapshot.child("Note").getValue(String.class);
        String distance = dataSnapshot.child("Distance").getValue(String.class);
        String dateTime = dataSnapshot.child("Datetime").getValue(String.class);
        Boolean saveOrder = dataSnapshot.child("Save Order").getValue(Boolean.class);
        String userGetOrder = dataSnapshot.child("Shipper").getValue(String.class);
        Boolean showAgain = dataSnapshot.child("Show Again").getValue(Boolean.class);
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
        if(userGetOrder != null){
            orderTemp.setUserGetOrder(userGetOrder);
        }

        if (showAgain != null) {
            if (showAgain) {
                for (int i = 0; i < orderList.size(); i++) {
                    if (orderTemp.getOrderID().equals(orderList.get(i).getOrderID())) {
                        orderList.remove(i);
                        orderList.add(i, orderTemp);
                        adapter.notifyItemChanged(i);
                        adapter.notifyItemRangeChanged(i, orderList.size());
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }

            }
            if (!showAgain) {
                for (int i = 0; i < orderList.size(); i++) {
                    if (orderTemp.getOrderID().equals(orderList.get(i).getOrderID())) {
                        orderList.remove(i);
                        adapter.notifyItemRemoved(i);
                        adapter.notifyItemRangeChanged(i, orderList.size());
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }


    }



    private void deleteItemData(DataSnapshot dataSnapshot) {
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            String key = snapshot.getValue(String.class);
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getOrderID().equals(key)) {
                    orderList.remove(orderList.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
        adapter = new CustomAdapterListviewOrder(getContext(), orderList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Check key from server created by current user
     */
    /*private List<String> checkKeyList(List<String> keyServer) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < keyServer.size(); i++) {
            if (getCurrentUser().getEmail() != null) {
                if (keyServer.get(i).contains(EncodingFirebase.encodeString(getCurrentUser().getEmail()))) {
                    result.add(keyServer.get(i));
                }
            } else {
                Log.e("Error", "Email of current user is null");
            }
        }
        return result;
    }*/
    private boolean checkKey(String key) {
        if (getCurrentUser().getEmail() != null) {
            if (key.contains(EncodingFirebase.encodeString(getCurrentUser().getEmail()))) {
                return true;
            }
        }
        return false;
    }


    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

}
