package com.minh.findtheshipper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.helpers.SortOrderTempHelpers;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrderShipper;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.User;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListOrderShipperFragment extends android.support.v4.app.Fragment {
    private Realm realm;
    private ArrayList<OrderTemp> orderList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        handleProgressDialog();
        View view = inflater.inflate(R.layout.list_recycle_view, container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Realm.init(getActivity());
        initRealm();
        loadAllList();
        return view;

    }

    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    public void handleProgressDialog()
    {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle(R.string.please_wait);
        progressDialog.setMessage(getResources().getString(R.string.wait_server));
        progressDialog.show();
    }

    public void loadAllList()
    {
        try
        {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
            Query query = mDatabase.orderByChild("Datetime");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    orderList = new ArrayList<>();
                    for(DataSnapshot child: dataSnapshot.getChildren())
                    {
                        final String key = child.getKey();
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
                    try {
                        Collections.sort(orderList,new SortOrderTempHelpers());
                        adapter = new CustomAdapterListviewOrderShipper(getActivity(),orderList);
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();
                    }catch (Exception e)
                    {
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }catch (Exception e)
        {
        }
    }
}
