package com.minh.findtheshipper;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.minh.findtheshipper.models.Adapters.AdapterListviewOrderSaved;
import com.minh.findtheshipper.models.Order;
import java.util.ArrayList;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListOrderSavedShipperFragment extends Fragment {
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

    public void loadAllList()
    {
        try
        {
            orderList = new ArrayList<>();
            RealmResults<Order> orders = realm.where(Order.class).findAllSorted("dateTime", Sort.DESCENDING);
            for (int i = 0; i < orders.size(); i++)
            {
                orderList.add(orders.get(i));
            }
            adapter = new AdapterListviewOrderSaved(orderList);
            recyclerView.setAdapter(adapter);

        }catch (Exception e)
        {
        }
    }
    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }
}
