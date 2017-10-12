package com.minh.findtheshipper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.minh.findtheshipper.helpers.DialogHelpers;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentContainer extends FragmentActivity {

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private int badgerCount = 10;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.create_new_order);
        NavigationDrawer(toolbar);
        if(findViewById(R.id.fragmentContainer) != null)
        {
            if(savedInstanceState != null)
            {
                return;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        if(badgerCount >0)
        {
            ActionItemBadge.update(this,menu.findItem(R.id.item_notifycation),resizeImage(R.drawable.ic_notifycation,200,200), ActionItemBadge.BadgeStyles.RED, badgerCount);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_notifycation:
                showNotification();
                //badgerCount++;
                // ActionItemBadge.update(item,badgerCount);
                return true;
            default:  return super.onOptionsItemSelected(item);
        }
    }
    private void showNotification()
    {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final DialogHelpers dialogHelpers = new DialogHelpers();

        dialogHelpers.show(fragmentManager,"New fragment");

    }
    private Drawable resizeImage(int resId, int w, int h)
    {
        // load the origial Bitmap
        Bitmap BitmapOrg = BitmapFactory.decodeResource(getResources(), resId);
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        // calculate the scale
        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,width, height, matrix, true);
        return new BitmapDrawable(resizedBitmap);
    }
    public void NavigationDrawer(Toolbar toolbar) {
        new DrawerBuilder().withActivity(this).build();
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.create_new_order);
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.created_order);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.tutorials);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.profile);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.about_us);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.version);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.setting);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName(R.string.log_out);
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_home)
                .addProfiles(
                        new ProfileDrawerItem().withName("Minh").withEmail("Trịnh Văn Minh")//.withIcon(myUri)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        item4,
                        item5,
                        item6,
                        item7,
                        item8
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if(drawerItem.getIdentifier() ==1)
                        {

                        }
                        if(drawerItem.getIdentifier() == 2)
                        {

                        }

                        return false;
                    }
                })
                .build();


        item1.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_create_new));
        item2.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_list_order));
        item3.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_tutorials));
        item4.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_your_profile));
        item5.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_about));
        item6.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_version));
        item7.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_settings));
        item8.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE)
                .withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_logout));
        result.openDrawer();
        result.closeDrawer();
        result.getDrawerLayout();
    }


}
