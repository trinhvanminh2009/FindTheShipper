package com.minh.findtheshipper.Shipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.ShipperAdapters.CustomAdapterListviewOrderSaved;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.Order;
import com.minh.findtheshipper.models.RealmObject.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListOrderSavedShipperFragment extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recycle_view, container, false);
        RecyclerView.LayoutManager layoutManager;
        recyclerView =  view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
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

            Realm.init(getActivity());
            initRealm();
            ArrayList<Order> orderList;
            RecyclerView.Adapter adapter;
            orderList = new ArrayList<>();
            User user = getCurrentUser();
            RealmResults<Order> orders = user.getOrderListSave().where().equalTo("saveOrder", true).findAllSorted("dateTime", Sort.DESCENDING);
            orderList.addAll(orders);
            adapter = new CustomAdapterListviewOrderSaved(getActivity(), orderList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("Error", "In loadAllList ListOrderSavedShipperFragment");

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
