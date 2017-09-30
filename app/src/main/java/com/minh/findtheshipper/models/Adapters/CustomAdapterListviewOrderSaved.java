package com.minh.findtheshipper.models.Adapters;

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
import com.minh.findtheshipper.Shipper.DetailOrderShipperActivity;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.Order;

import java.util.List;

import io.realm.Realm;

/**
 * Created by trinh on 6/24/2017.
 */

public class CustomAdapterListviewOrderSaved extends RecyclerView.Adapter<CustomAdapterListviewOrderSaved.ViewHolder> {

    private List<Order> orderList;
    private Context context;
    private Realm realm;

    @Override
    public CustomAdapterListviewOrderSaved.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_item, parent, false);
        Realm.init(view.getContext());
        initRealm();
        return new ViewHolder(view);
    }

    public CustomAdapterListviewOrderSaved(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Order order = orderList.get(position);
        /**
         * Query name from FireBase using id in orders
         * */
        final EncodingFirebase encodingFireBase = new EncodingFirebase();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = encodingFireBase.getEmailFromUserID(order.getOrderID());
                String nameCreator = dataSnapshot.child(key).child("Name").getValue(String.class);
                holder.txtUserName.setText(nameCreator);
                String url = dataSnapshot.child(key).child("Avatar").getValue(String.class);
                Glide.with(context).load(encodingFireBase.decodeString(url)).apply(RequestOptions.circleCropTransform()).thumbnail(0.7f).into(holder.imgAvatar);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Depending on server. How fast is it.
        DatabaseReference orderDatabase = FirebaseDatabase.getInstance().getReference("order");
        orderDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = order.getOrderID();
                String timeCreated = dataSnapshot.child(key).child("Datetime").getValue(String.class);
                TimeAgoHelpers timeAgoHelpers = new TimeAgoHelpers();
                String timeAgo = timeAgoHelpers.getTimeAgo(timeCreated, context);
                holder.txtTimeAgo.setText(timeAgo);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        String startPlace = " " + EncodingFirebase.getShortAddress(order.getStartPoint());
        String finishPlace = " " + EncodingFirebase.getShortAddress(order.getFinishPoint());
        String distance = " " + order.getDistance();
        holder.txtStartPlace.setText(startPlace);
        holder.txtFinishPlace.setText(finishPlace);
        holder.txtPrice.setText(order.getShipMoney());
        holder.txtDistance.setText(distance);
        holder.txtPhoneNumber.setText(order.getPhoneNumber());
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailOrderShipperActivity.class);
                String[] orderKey = new String[2];
                orderKey[0] = order.getOrderID();
                orderKey[1] = "History";
                intent.putExtra("orderKey", orderKey);
                v.getContext().startActivity(intent);
            }
        });
    }


    public void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
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

        private ViewHolder(View view) {
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
