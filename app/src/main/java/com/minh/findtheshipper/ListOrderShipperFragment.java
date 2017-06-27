package com.minh.findtheshipper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrderShipper;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.User;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListOrderShipperFragment extends Fragment {
    private Realm realm;
    private ArrayList<Order> orderList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_recycle_view, container,false);
        recyclerView =(RecyclerView)view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        realm.init(getActivity());
        initRealm();
        loadAllList();
       // addUser();
      //  loadAllUser();
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

    public void addUser()
    {
        try{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    User user = realm.createObject(User.class,"trinhvanminh2009");
                    user.setPhoneNumber("01647976713");
                    user.setFullName("Trịnh Văn Minh");
                    user.setAvatar(R.drawable.ic_your_profile);
                    RealmResults<Order> orders = realm.where(Order.class).findAllSorted("dateTime", Sort.DESCENDING);
                    for (int i = 0; i < orders.size(); i++)
                    {
                        user.getOrderArrayList().add(orders.get(i));
                    }
                    realm.insertOrUpdate(user);
                }
            });
        }catch (Exception e)
        {

        }
    }
    public void loadAllUser()
    {
        try
        {
            RealmResults<User> users = realm.where(User.class).findAll();
            for (int i = 0; i < users.size(); i++)
            {
              TastyToast.makeText(getActivity(), users.get(i).getOrderArrayList().toString(),TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
            }

        }catch (Exception e)
        {
        }
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
            adapter = new CustomAdapterListviewOrderShipper(getActivity(),orderList);
            recyclerView.setAdapter(adapter);
        }catch (Exception e)
        {
        }
    }

}
