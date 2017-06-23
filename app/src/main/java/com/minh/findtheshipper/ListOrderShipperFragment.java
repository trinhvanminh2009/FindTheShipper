package com.minh.findtheshipper;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.minh.findtheshipper.helpers.DialogHelpers;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrderShipper;
import com.minh.findtheshipper.models.Order;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ListOrderShipperFragment extends Fragment {

    private int badgerCount = 10;
    private Realm realm;
    private ListView listView;

    private ArrayList<Order> orderList;
    private CustomAdapterListviewOrderShipper customAdapterListviewOrderShipper;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        realm.init(getActivity());
        initRealm();
        View view = inflater.inflate(R.layout.activity_list_order_shipper, container,false);
        listView = (ListView)view.findViewById(R.id.listOrderShipper);
        orderList = new ArrayList<>();
        RealmResults<Order> orders = realm.where(Order.class).findAllSorted("dateTime", Sort.DESCENDING);
        for (int i = 0; i < orders.size(); i++)
        {
            orderList.add(orders.get(i));
        }
        customAdapterListviewOrderShipper = new CustomAdapterListviewOrderShipper(getActivity(), orderList);
        listView.setAdapter(customAdapterListviewOrderShipper);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list,menu);
        if(badgerCount >0)
        {
            ActionItemBadge.update(getActivity(),menu.findItem(R.id.item_notifycation),resizeImage(R.drawable.ic_notifycation,200,200), ActionItemBadge.BadgeStyles.RED, badgerCount);

        }
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

        DialogHelpers dialogHelpers = new DialogHelpers();
       // dialogHelpers.show(getActivity().getFragmentManager(),"dd");


    }



    private Drawable resizeImage(int resId, int w, int h)
    {
        // load the origial Bitmap
        Bitmap BitmapOrg = BitmapFactory.decodeResource(getResources(), resId);
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;
        // calculate the scale
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,width, height, matrix, true);
        return new BitmapDrawable(resizedBitmap);
    }
}
