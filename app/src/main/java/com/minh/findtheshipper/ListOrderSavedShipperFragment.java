package com.minh.findtheshipper;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrderSaved;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.User;

import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListOrderSavedShipperFragment extends android.support.v4.app.Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Order> orderList;
    private Realm realm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recycle_view, container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadAllList();
        return view;
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
            RealmResults<Order> orders = user.getOrderListSave().where().equalTo("saveOrder",true).findAllSorted("dateTime",Sort.DESCENDING);
            for (int i = 0; i < orders.size(); i++)
            {
                orderList.add(orders.get(i));
            }
            adapter = new CustomAdapterListviewOrderSaved(getActivity(),orderList);
            recyclerView.setAdapter(adapter);
        }catch (Exception e){}

    }
    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    private User getCurrentUser()
    {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        User user = realm.where(User.class).beginGroup().equalTo("email",currentUser.getEmail()).endGroup().findFirst();
        return user;
    }
}
