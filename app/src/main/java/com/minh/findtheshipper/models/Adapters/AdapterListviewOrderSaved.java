package com.minh.findtheshipper.models.Adapters;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.utils.AnimationUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by trinh on 6/24/2017.
 */

public class AdapterListviewOrderSaved extends RecyclerView.Adapter<AdapterListviewOrderSaved.ViewHolder> {

    private List<Order>orderList;
    private int previousPosition = 0;

    @Override
    public AdapterListviewOrderSaved.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_shipper_saved,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public AdapterListviewOrderSaved(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Order order = orderList.get(position);
        holder.txtStart.setText(order.getStartPoint());
        holder.txtfisnih.setText(order.getFinishPoint());
        holder.txtAdvancedMoney.setText(order.getAdvancedMoney());
        holder.txtShipMoney.setText(order.getShipMoney());
        holder.txtNote.setText(order.getNote());
        holder.txtPhoneNumber.setText(order.getPhoneNumber());
        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempPhone = order.getPhoneNumber().replaceAll("[^0-9]+", " ");
                List<String> phoneNumber = Arrays.asList(tempPhone.trim().split(" "));
                Intent intentPhone = new Intent(Intent.ACTION_CALL);
                intentPhone.setData(Uri.parse("tel:"+phoneNumber.get(0)));
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                v.getContext().startActivity(intentPhone);
            }
        });
        AnimationUtils animationUtils = new AnimationUtils();
        if(position >previousPosition)
        {
            animationUtils.animate(holder,true);
            previousPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtStart;
        private TextView txtfisnih;
        private TextView txtAdvancedMoney;
        private TextView txtShipMoney;
        private TextView txtNote;
        private TextView txtPhoneNumber;
        private Button btnComment;
        private Button btnCall;
        private Button btnDelete;



        public ViewHolder(View itemView) {
            super(itemView);
            txtStart = (TextView)itemView.findViewById(R.id.txtStatingPointShipper);
            txtfisnih = (TextView)itemView.findViewById(R.id.txtFinishPointShipper);
            txtAdvancedMoney = (TextView)itemView.findViewById(R.id.txtAdvancedMoneyShipper);
            txtNote = (TextView)itemView.findViewById(R.id.txtNoteShipper);
            txtShipMoney = (TextView)itemView.findViewById(R.id.txtShipMoneyShipper);
            txtPhoneNumber = (TextView)itemView.findViewById(R.id.txtPhoneNumberShipper);
            btnComment = (Button)itemView.findViewById(R.id.btnComment);
            btnCall = (Button)itemView.findViewById(R.id.btnCall);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }
}
