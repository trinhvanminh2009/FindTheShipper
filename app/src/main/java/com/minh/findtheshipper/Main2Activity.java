package com.minh.findtheshipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.minh.findtheshipper.models.Adapters.AdapterListviewOrderSaved;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrder;
import com.minh.findtheshipper.models.Order;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Order> orderList;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Realm.init(this);
        recyclerView =(RecyclerView)findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        initRealm();
        loadAllList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
