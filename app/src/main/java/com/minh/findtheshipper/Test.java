package com.minh.findtheshipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by trinh on 6/16/2017.
 */

public class Test extends BaseActivity {

    @BindView(R.id.toolBar) Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test");
        NavigationDrawer(toolbar);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_fragment;
    }
}
