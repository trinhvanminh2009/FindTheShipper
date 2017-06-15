package com.minh.findtheshipper;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrder;
import com.minh.findtheshipper.models.Order;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class CreatedOrderActivity extends BaseActivity {

    @BindView(R.id.toolBar) Toolbar toolbar;
    @BindView(R.id.listOrder) ListView listViewOrder;
    private ArrayList<Order> orderList;
    private CustomAdapterListviewOrder customAdapterListviewOrder;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_order);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        realm.init(CreatedOrderActivity.this);
        initRealm();
        loadAllList();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawer(toolbar);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_created_order;
    }

    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    public void loadAllList()
    {
        orderList = new ArrayList<>();
        RealmResults<Order> orders = realm.where(Order.class).findAll();
        for (int i = 0; i < orders.size(); i++)
        {
            orderList.add(orders.get(i));
        }
        customAdapterListviewOrder = new CustomAdapterListviewOrder(CreatedOrderActivity.this, orderList);

        listViewOrder.setAdapter(customAdapterListviewOrder);
    }

}
