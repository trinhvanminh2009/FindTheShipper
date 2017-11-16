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
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.OrderTemp;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

import io.realm.Realm;

/**
 * Created by trinh on 6/14/2017.
 */

public class CustomAdapterListviewOrder  extends RecyclerView.Adapter<CustomAdapterListviewOrder.ViewHolder>{
    private Context context;
    private List<OrderTemp> orderList;
    private Realm realm;

    public CustomAdapterListviewOrder(Context context, List<OrderTemp> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public CustomAdapterListviewOrder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order,parent,false);
        CustomAdapterListviewOrder.ViewHolder viewHolder = new CustomAdapterListviewOrder.ViewHolder(view);
        Realm.init(context);
        initRealm();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapterListviewOrder.ViewHolder holder, int position) {
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
        final OrderTemp order = orderList.get(position);
        final CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        Glide.with(context).load(currentUser.getAvatar()).apply(RequestOptions.circleCropTransform()).thumbnail(0.5f).into(holder.userImage);
        /**Get email from server have character '_' inside
         * First: Get email
         * Second: Get the username of this user, not email
         * Third: Show it*/
        if(order.getUserGetOrder() != null)
        {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String key = order.getUserGetOrder();
                    String nameCreator = dataSnapshot.child(key).child("Name").getValue(String.class);
                    TastyToast.makeText(context,nameCreator,TastyToast.LENGTH_SHORT,TastyToast.INFO);
                    holder.txtUserName.setText(nameCreator);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        String timeAgo = context.getString(R.string.order_last_updated) +
                TimeAgoHelpers.getTimeAgo(order.getDateTime(), context);
        String startPlace = " " + EncodingFirebase.getShortAddress(order.getStartPoint());
        String finishPlace = " " + EncodingFirebase.getShortAddress(order.getFinishPoint());
        holder.txtStartPlace.setText(startPlace);
        holder.txtFinishPlace.setText(finishPlace);
        holder.txtShipMoney.setText(order.getShipMoney());
        holder.txtDistance.setText(order.getDistance());
        holder.txtPhoneNumber.setText(order.getPhoneNumber());
        holder.txtDatetime.setText(timeAgo);

    }


    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtStartPlace;
        private TextView txtFinishPlace;
        private TextView txtShipMoney;
        private TextView txtDistance;
        private TextView txtPhoneNumber;
        private TextView txtDatetime;
        private TextView txtUserName;
        private ImageView userImage;
        private RatingBar ratingBarOrder;
        private StateProgressBar stateProgressBar;
        String[] descriptionData = {"Find", "Confirm", "Delivery", "Done"};



        public ViewHolder(View view) {
            super(view);
            txtStartPlace = (TextView)view.findViewById(R.id.txtStartPlace);
            txtFinishPlace = (TextView)view.findViewById(R.id.txtFinishPlace);
            txtShipMoney = (TextView)view.findViewById(R.id.txtPrice);
            txtDistance = (TextView)view.findViewById(R.id.txtDistance);
            txtPhoneNumber = (TextView)view.findViewById(R.id.txtPhoneNumber);
            txtDatetime = (TextView)view.findViewById(R.id.txtTimeAgo);
            txtUserName = (TextView)view.findViewById(R.id.txtUserName);
            userImage = (ImageView)view.findViewById(R.id.imgUserImage);
            ratingBarOrder = (RatingBar) view.findViewById(R.id.ratingOrder);
            stateProgressBar = (StateProgressBar)view.findViewById(R.id.stateProgressBar);
            stateProgressBar.setStateDescriptionData(descriptionData);
        }
    }
}
