package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Order;

import java.util.List;

/**
 * Created by trinh on 6/14/2017.
 */

public class CustomAdapterListviewOrder  extends ArrayAdapter<Order>{
    private Context context;
    private List<Order> orderList;

    public CustomAdapterListviewOrder(@NonNull Context context,  @NonNull List<Order> objects) {
        super(context, 0, objects);
        this.context = context;
        this.orderList = objects;
    }


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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_order,parent,false);
        TextView txtStatus = (TextView)view.findViewById(R.id.txtStatus);
        TextView txtStartPlace = (TextView)view.findViewById(R.id.txtStart);
        TextView txtFinishPlace = (TextView)view.findViewById(R.id.txtFinish);
        TextView txtAdvancedMoney = (TextView)view.findViewById(R.id.txtAdvancedMoney);
        TextView txtShipMoney = (TextView)view.findViewById(R.id.txtShipMoney);
        TextView txtDistance = (TextView)view.findViewById(R.id.txtDistance);
        TextView txtNote = (TextView)view.findViewById(R.id.txtNote);
        TextView txtPhoneNumber = (TextView)view.findViewById(R.id.txtPhoneNumber);
        TextView txtDatetime = (TextView)view.findViewById(R.id.txtDatetime);
        Order order = orderList.get(position);
        txtStatus.setText(order.getStatus());
        txtStartPlace.setText(order.getStartPoint());
        txtFinishPlace.setText(order.getFinishPoint());
        txtAdvancedMoney.setText(order.getAdvancedMoney());
        txtShipMoney.setText(order.getShipMoney());
       // txtDistance.setText(order.getDistance());
        txtNote.setText(order.getNote());
        txtPhoneNumber.setText(order.getPhoneNumber());
        txtDatetime.setText(order.getDateTime());
        Button btnEdit = (Button)view.findViewById(R.id.btnEdit);
        Button btnFindAgain = (Button)view.findViewById(R.id.btnFindAgain);
        Button btnOrderSucess = (Button)view.findViewById(R.id.btnOrderSuccess);
        Button btnCallAgain = (Button)view.findViewById(R.id.btnCallAgain);
        return view;

    }
}
