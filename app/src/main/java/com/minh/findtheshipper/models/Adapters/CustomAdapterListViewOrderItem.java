package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
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
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.OrderTemp;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

/**
 * Created by trinh on 9/13/2017.
 */

public class CustomAdapterListViewOrderItem extends RecyclerView.Adapter<CustomAdapterListViewOrderItem.ViewHolder> {
    private List<OrderTemp> orderList;
    private Context context;

    public CustomAdapterListViewOrderItem(List<OrderTemp> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_item, parent, false);
        CustomAdapterListViewOrderItem.ViewHolder viewHolder =
                new CustomAdapterListViewOrderItem.ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OrderTemp order = orderList.get(position);
        TimeAgoHelpers timeAgoHelpers = new TimeAgoHelpers();
        final EncodingFirebase encodingFirebase = new EncodingFirebase();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = encodingFirebase.getEmailFromUserID(order.getOrderID());
                String nameCreator = dataSnapshot.child(key).child("Name").getValue(String.class);
                String url = dataSnapshot.child(key).child("Avatar").getValue(String.class);
                holder.txtUserName.setText(nameCreator);
                Glide.with(context).load(encodingFirebase.decodeString(url)).apply(RequestOptions.circleCropTransform()).thumbnail(0.7f).into(holder.imgAvatar);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String phoneNumber = "- " + order.getPhoneNumber();
        holder.txtPhoneNumber.setText(phoneNumber);
        holder.txtStartPlace.setText(EncodingFirebase.getShortAddress(order.getStartPoint()));
        holder.txtFinishPlace.setText(EncodingFirebase.getShortAddress(order.getFinishPoint()));
        holder.txtPrice.setText(order.getShipMoney());
        holder.txtTimeAgo.setText(timeAgoHelpers.getTimeAgo(order.getDateTime(), context));
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(context,"Clicked on "+position , TastyToast.LENGTH_LONG,TastyToast.INFO);
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
        }
    }
}
