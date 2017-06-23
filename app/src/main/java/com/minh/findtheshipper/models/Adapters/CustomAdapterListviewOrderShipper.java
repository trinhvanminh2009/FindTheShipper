package com.minh.findtheshipper.models.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Order;
import com.sdsmdg.tastytoast.TastyToast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by trinh on 6/22/2017.
 */

public class CustomAdapterListviewOrderShipper extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orderList;

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public CustomAdapterListviewOrderShipper(@NonNull Context context, @NonNull List<Order> objects) {
        super(context, 0, objects);
        this.context = context;
        this.orderList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(R.layout.list_order_shipper, parent, false);
        TextView startingPoint = (TextView) view.findViewById(R.id.txtStatingPointShipper);
        TextView finishPoint = (TextView) view.findViewById(R.id.txtFinishPointShipper);
        TextView advancedMoney = (TextView) view.findViewById(R.id.txtAdvancedMoneyShipper);
        TextView shipMoney = (TextView) view.findViewById(R.id.txtShipMoneyShipper);
        TextView note = (TextView) view.findViewById(R.id.txtNoteShipper);
        final TextView phoneNumber = (TextView) view.findViewById(R.id.txtPhoneNumberShipper);
        Button btnComment = (Button) view.findViewById(R.id.btnComment);
        Button btnCall = (Button) view.findViewById(R.id.btnCall);
        Button btnSave = (Button) view.findViewById(R.id.btnSave);
        final Order order = orderList.get(position);
        startingPoint.setText(order.getStartPoint());
        finishPoint.setText(order.getFinishPoint());
        advancedMoney.setText(order.getAdvancedMoney());
        shipMoney.setText(order.getShipMoney());
        note.setText(order.getNote());
        phoneNumber.setText(order.getPhoneNumber());
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tempPhone = order.getPhoneNumber().replaceAll("[^0-9]+", " ");
                List<String> phoneNumber = Arrays.asList(tempPhone.trim().split(" "));
                TastyToast.makeText(getContext(),phoneNumber.get(0),TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                Intent intentPhone = new Intent(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel:"+phoneNumber.get(0)));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                view.getContext().startActivity(intentPhone);
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }


}
