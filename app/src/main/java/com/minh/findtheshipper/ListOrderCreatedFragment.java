package com.minh.findtheshipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrder;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListOrderCreatedFragment extends android.support.v4.app.Fragment {

    private Realm realm;
    private ArrayList<Order> orderList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recycle_view,container,false);
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

    public void loadAllList()
    {
        try{
            Realm.init(getActivity());
            initRealm();
            orderList = new ArrayList<>();
            User user = getCurrentUser();
            RealmResults<Order> orders = user.getOrderArrayList().where().findAllSorted("dateTime",Sort.ASCENDING);
            for (int i = 0; i < orders.size(); i++)
            {
                orderList.add(orders.get(i));
            }
            adapter = new CustomAdapterListviewOrder(getActivity(),orderList);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){}

    }

    private User getCurrentUser()
    {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        User user = realm.where(User.class).beginGroup().equalTo("email",currentUser.getEmail()).endGroup().findFirst();
        return user;
    }

    public void deleteAll()
    {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final RealmResults<Order> results = realm.where(Order.class).findAll();
                results.deleteAllFromRealm();
            }
        });

    }

}
