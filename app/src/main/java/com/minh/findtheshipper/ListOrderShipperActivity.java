package com.minh.findtheshipper;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListOrderShipperActivity extends BaseActivityShipper {

    @BindView(R.id.toolBar) Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List order");
        NavigationDrawer(toolbar);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_list_order_shipper;
    }
}
