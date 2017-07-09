package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Order;

import java.util.List;

/**
 * Created by trinh on 6/14/2017.
 */

public class CustomAdapterListviewOrder  extends RecyclerView.Adapter<CustomAdapterListviewOrder.ViewHolder>{
    private Context context;
    private List<Order> orderList;


    public CustomAdapterListviewOrder(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public CustomAdapterListviewOrder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order,parent,false);
        CustomAdapterListviewOrder.ViewHolder viewHolder = new CustomAdapterListviewOrder.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapterListviewOrder.ViewHolder holder, int position) {
        final Order order = orderList.get(position);
        holder.txtStatus.setText(order.getStatus());
        holder.txtStartPlace.setText(order.getStartPoint());
        holder.txtFinishPlace.setText(order.getFinishPoint());
        holder.txtAdvancedMoney.setText(order.getAdvancedMoney());
        holder.txtShipMoney.setText(order.getShipMoney());
        holder.txtDistance.setText(order.getDistance());
        holder.txtNote.setText(order.getNote());
        holder.txtPhoneNumber.setText(order.getPhoneNumber());
        holder.txtDatetime.setText(R.string.order_last_updated+order.getDateTime());
        if(holder.txtStatus.getText() == "")
        {
            holder.haveStatus.setVisibility(View.GONE);

        }
        if(holder.txtStatus.getText() != "")
        {
            holder.nonStatus.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtStatus;
        private TextView txtStartPlace;
        private TextView txtFinishPlace;
        private TextView txtAdvancedMoney;
        private TextView txtShipMoney;
        private TextView txtDistance;
        private TextView txtNote;
        private TextView txtPhoneNumber;
        private TextView txtDatetime;
        private LinearLayout nonStatus;
        private LinearLayout haveStatus;
        private Button btnEdit;
        private Button btnFindAgain;
        private Button btnOrderSucess;
        private Button btnCallAgain;
        public ViewHolder(View view) {
            super(view);
            txtStatus = (TextView)view.findViewById(R.id.txtStatus);
            txtStartPlace = (TextView)view.findViewById(R.id.txtStart);
            txtFinishPlace = (TextView)view.findViewById(R.id.txtFinish);
            txtAdvancedMoney = (TextView)view.findViewById(R.id.txtAdvancedMoney);
            txtShipMoney = (TextView)view.findViewById(R.id.txtShipMoney);
            txtDistance = (TextView)view.findViewById(R.id.txtDistanceOrder);
            txtNote = (TextView)view.findViewById(R.id.txtNote);
            txtPhoneNumber = (TextView)view.findViewById(R.id.txtPhoneNumber);
            txtDatetime = (TextView)view.findViewById(R.id.txtDatetime);
            nonStatus = (LinearLayout)view.findViewById(R.id.nonStatus);
            haveStatus = (LinearLayout)view.findViewById(R.id.haveStatus);
            btnEdit = (Button)view.findViewById(R.id.btnEdit);
            btnFindAgain = (Button)view.findViewById(R.id.btnFindAgain);
            btnOrderSucess = (Button)view.findViewById(R.id.btnOrderSuccess);
            btnCallAgain = (Button)view.findViewById(R.id.btnCallAgain);
        }
    }
}
