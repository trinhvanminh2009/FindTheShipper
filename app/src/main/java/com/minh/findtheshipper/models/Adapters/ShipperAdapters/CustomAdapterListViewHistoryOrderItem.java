package com.minh.findtheshipper.models.Adapters.ShipperAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.Shipper.DetailOrderHistoryActivity;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.OrderTemp;

import java.util.List;

/**
 * Created by trinh on 11/11/2017.
 * This class only handle about history order item
 */

public class CustomAdapterListViewHistoryOrderItem  extends RecyclerView.Adapter<CustomAdapterListViewHistoryOrderItem.ViewHolder> {
    private List<OrderTemp> orderList;
    private Context context;

    public CustomAdapterListViewHistoryOrderItem(List<OrderTemp> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public CustomAdapterListViewHistoryOrderItem.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_item, parent, false);
        return  new CustomAdapterListViewHistoryOrderItem.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomAdapterListViewHistoryOrderItem.ViewHolder holder, int position) {
        final OrderTemp order = orderList.get(position);
        TimeAgoHelpers timeAgoHelpers = new TimeAgoHelpers();
        final EncodingFirebase encodingFireBase = new EncodingFirebase();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
        final String[] key = {""};
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                key[0] = EncodingFirebase.getEmailFromUserID(order.getOrderID());
                String nameCreator = dataSnapshot.child(key[0]).child("Name").getValue(String.class);
                String url = dataSnapshot.child(key[0]).child("Avatar").getValue(String.class);
                holder.txtUserName.setText(nameCreator);
                Glide.with(context).load(EncodingFirebase.decodeString(url)).apply(RequestOptions.circleCropTransform()).thumbnail(0.7f).into(holder.imgAvatar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String phoneNumber = "" + order.getPhoneNumber();
        String startPlace = " " + EncodingFirebase.getShortAddress(order.getStartPoint());
        String finishPlace = " " + EncodingFirebase.getShortAddress(order.getFinishPoint());
        String distance = " " + order.getDistance();
        holder.txtDistance.setText(distance);
        holder.txtPhoneNumber.setText(phoneNumber);
        holder.txtStartPlace.setText(startPlace);
        holder.txtFinishPlace.setText(finishPlace);
        holder.txtPrice.setText(order.getShipMoney());
        holder.txtTimeAgo.setText( TimeAgoHelpers.getTimeAgo(order.getDateTime(), context));
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailOrderHistoryActivity.class);
                String[] orderKey = new String[2];
                orderKey[0] = order.getOrderID();
                orderKey[1] = "Present";//this key to know order saved or order from server
                intent.putExtra("orderKey",orderKey);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView txtUserName;
        private TextView txtStartPlace;
        private TextView txtFinishPlace;
        private TextView txtPhoneNumber;
        private TextView txtPrice;
        private RatingBar ratingBarOrder;
        private TextView txtTimeAgo;
        private TextView txtDistance;

        public ViewHolder(View view) {
            super(view);
            imgAvatar = (ImageView) view.findViewById(R.id.imgUserImage);
            txtUserName = (TextView) view.findViewById(R.id.txtUserName);
            txtStartPlace = (TextView) view.findViewById(R.id.txtStartPlace);
            txtFinishPlace = (TextView) view.findViewById(R.id.txtFinishPlace);
            txtPhoneNumber = (TextView) view.findViewById(R.id.txtPhoneNumber);
            ratingBarOrder = (RatingBar) view.findViewById(R.id.ratingOrder);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            txtTimeAgo = (TextView) view.findViewById(R.id.txtTimeAgo);
            txtDistance = (TextView) view.findViewById(R.id.txtDistance);
        }

    }
}
