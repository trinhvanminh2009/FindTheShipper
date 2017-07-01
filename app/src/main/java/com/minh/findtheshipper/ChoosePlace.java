package com.minh.findtheshipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoosePlace extends AppCompatActivity {
    @BindView(R.id.btnHaNoi)Button btnHaHoi;
    @BindView(R.id.btnHoChiMinh)Button btnHoChiMinh;
    @BindView(R.id.btnShipMode) Button btnShipMode;
    @BindView(R.id.btnShopMode) Button btnShopMode;
    @BindView(R.id.chooseMode)LinearLayout chooseMode;
    @BindView(R.id.choosePlace)LinearLayout choosePlace;
    private Animation animationLeft;
    private Animation animationRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_place);
        ButterKnife.bind(this);
        animationLeft = AnimationUtils.loadAnimation(this,R.anim.button_left);
        animationRight = AnimationUtils.loadAnimation(this,R.anim.button_right);
        btnHaHoi.setAnimation(animationLeft);
        btnHoChiMinh.setAnimation(animationRight);
    }

    @OnClick(R.id.btnHaNoi)
    public void ChooseHaNoi()
    {
       ChangeVisibility();
    }

    @OnClick(R.id.btnHoChiMinh)
    public void ChooseHoChiMinh()
    {
        ChangeVisibility();
    }

    private void ChangeVisibility()
    {
        choosePlace.setVisibility(View.GONE);
        chooseMode.setVisibility(View.VISIBLE);
        animationLeft = AnimationUtils.loadAnimation(this,R.anim.button_left);
        animationRight = AnimationUtils.loadAnimation(this,R.anim.button_right);
        btnShipMode.setAnimation(animationLeft);
        btnShopMode.setAnimation(animationRight);
    }

    @OnClick(R.id.btnShipMode)
    public void ChooseShipMode()
    {
        startActivity(new Intent(ChoosePlace.this, FragmentContainerShipper.class));
    }

    @OnClick(R.id.btnShopMode)
    public void ChooseShopMode()
    {
        startActivity(new Intent(ChoosePlace.this, HandleMapsActivity.class));

    }

}
