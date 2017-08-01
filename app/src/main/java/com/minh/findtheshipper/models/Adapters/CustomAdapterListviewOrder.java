package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.EncodingFirebase;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.OrderTemp;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
            holder.layoutNameAndAvatar.setVisibility(View.VISIBLE);
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String key = order.getUserGetOrder();
                    String nameCreator = dataSnapshot.child(key).child("Name").getValue(String.class);
                    holder.txtUserName.setText(nameCreator);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            holder.txtStatus.setText(context.getResources().getText(R.string.order_status_getting_started));
        }
        else
        {
            holder.layoutNameAndAvatar.setVisibility(View.GONE);
            holder.txtStatus.setText(context.getResources().getText(R.string.order_status_waiting_shipper));
        }


        holder.txtStartPlace.setText(order.getStartPoint());
        holder.txtFinishPlace.setText(order.getFinishPoint());
        holder.txtAdvancedMoney.setText(order.getAdvancedMoney());
        holder.txtShipMoney.setText(order.getShipMoney());
        holder.txtDistance.setText(order.getDistance());
        holder.txtNote.setText(order.getNote());
        holder.txtPhoneNumber.setText(order.getPhoneNumber());
        TimeAgoHelpers timeAgoHelpers = new TimeAgoHelpers();
        holder.txtDatetime.setText( context.getResources().getText(R.string.order_last_updated)+ timeAgoHelpers.getTimeAgo(order.getDateTime(),context));
        if(holder.txtStatus.getText() == "")
        {
            holder.haveStatus.setVisibility(View.GONE);
        }
        if(holder.txtStatus.getText() != "")
        {
            holder.nonStatus.setVisibility(View.GONE);
        }
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
        private TextView txtStatus;
        private TextView txtStartPlace;
        private TextView txtFinishPlace;
        private TextView txtAdvancedMoney;
        private TextView txtShipMoney;
        private TextView txtDistance;
        private TextView txtNote;
        private TextView txtPhoneNumber;
        private TextView txtDatetime;
        private TextView txtUserName;
        private ImageView userImage;
        private LinearLayout nonStatus;
        private LinearLayout haveStatus;
        private LinearLayout layoutNameAndAvatar;
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
            txtUserName = (TextView)view.findViewById(R.id.txtUserName);
            userImage = (ImageView)view.findViewById(R.id.userImage);
            layoutNameAndAvatar = (LinearLayout)view.findViewById(R.id.layoutNameAndAvatar);
        }
    }
}
