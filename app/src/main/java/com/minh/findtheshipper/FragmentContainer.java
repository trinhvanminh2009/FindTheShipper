package com.minh.findtheshipper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentContainer extends FragmentActivity {

    /***
     * This Fragment display for the shipper
     */
    @BindView(R.id.toolBar) Toolbar toolbar;
    private static final int REQUEST= 112;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        ButterKnife.bind(this);
        NavigationDrawer(toolbar);
        /**
         * Have to request permissions right here . Because can't request permissions in ArrayAdapter.
         */
        if(Build.VERSION.SDK_INT >= 23)
        {
            String[] PERMISSIONS = {
                    Manifest.permission.CALL_PHONE
            };
            if(!hasPermissions(context,PERMISSIONS))
            {
                ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, REQUEST );
            }
            else {

            }

        }


    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                  TastyToast.makeText(context, "Permission denied", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
                }
        }
    }

    public void NavigationDrawer(Toolbar toolbar) {

        //   Uri myUri = Uri.parse(listProfile[3]);
        // Log.d("myTags",myUri.toString());


        new DrawerBuilder().withActivity(this).build();
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.list_order_shipper);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.list_order_saved_shipper);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.pay_coin_shipper);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.buy_package_shipper);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.tutorials);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.profile);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.about_us);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName(R.string.version);
        PrimaryDrawerItem item9 = new PrimaryDrawerItem().withIdentifier(9).withName(R.string.setting);
        PrimaryDrawerItem item10 = new PrimaryDrawerItem().withIdentifier(10).withName(R.string.log_out);
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
                        item8,item9,item10
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
                        if(drawerItem.getIdentifier() ==3)
                        {

                        }
                        if(drawerItem.getIdentifier() == 4)
                        {

                        }
                        if(drawerItem.getIdentifier() ==5)
                        {

                        }
                        if(drawerItem.getIdentifier() == 6)
                        {

                        }
                        if(drawerItem.getIdentifier() ==7)
                        {

                        }
                        if(drawerItem.getIdentifier() == 8)
                        {

                        }
                        if(drawerItem.getIdentifier() ==9)
                        {

                        }
                        if(drawerItem.getIdentifier() == 10)
                        {

                        }
                        return false;
                    }
                })
                .build();


        item1.withBadge("1").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_list_order));
        item2.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_list_order_saved));
        item3.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_buy_coin));
        item4.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_package));
        item5.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_tutorials));
        item6.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_your_profile));
        item7.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_about));
        item8.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_version));
        item9.withBadge("19").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_settings));
        item10.withBadge("5").withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).
                withColorRes(R.color.md_green_900)).withIcon(getResources().getDrawable(R.drawable.ic_logout));

        result.openDrawer();
        result.closeDrawer();
        result.getDrawerLayout();
    }
}