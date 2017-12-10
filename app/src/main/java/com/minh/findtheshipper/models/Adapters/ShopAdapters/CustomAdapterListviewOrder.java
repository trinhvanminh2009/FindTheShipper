package com.minh.findtheshipper.models.Adapters.ShopAdapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.minh.findtheshipper.Shop.DetailOrderShopActivity;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.helpers.OneSignalHelpers;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.User;
import com.sdsmdg.tastytoast.TastyToast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import at.markushi.ui.CircleButton;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;

/**
 * Created by trinh on 6/14/2017.
 * This class about List order Created
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
        final String key = order.getOrderID();
        final CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        Glide.with(context).load(currentUser.getAvatar()).apply(RequestOptions.circleCropTransform())
                .thumbnail(0.5f).into(holder.userImage);
        /**
         *
          Get email from server have character '_' inside
          First: Get email
          Second: Get the username of this user, not email
          Third: Show it*/
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
        final String startPlace = " " + EncodingFirebase.getShortAddress(order.getStartPoint());
        String finishPlace = " " + EncodingFirebase.getShortAddress(order.getFinishPoint());
        holder.txtStartPlace.setText(startPlace);
        holder.txtFinishPlace.setText(finishPlace);
        holder.txtShipMoney.setText(order.getShipMoney());
        holder.txtDistance.setText(order.getDistance());
        holder.txtPhoneNumber.setText(order.getPhoneNumber());
        holder.txtDatetime.setText(timeAgo);
        /**Start get state order to update
         * Get update for all orders for any order
         * */
        final String[] descriptionData = {"Find", "Confirm", "Delivery", "Done"};
        holder.stateProgressBar.setStateDescriptionData(descriptionData);
        final DatabaseReference orderDataBase = FirebaseDatabase.getInstance()
                .getReference("order").child(key).child("State Order");

        orderDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String currentState = dataSnapshot.getValue(String.class);
                StateProgressBar.StateNumber stateNumber = null;
                if (currentState != null) {

                    if (currentState.equals("1")) {
                        stateNumber = StateProgressBar.StateNumber.ONE;
                    }
                    if (currentState.equals("2")) {
                        stateNumber = StateProgressBar.StateNumber.TWO;
                    }
                    if (currentState.equals("3")) {
                        stateNumber = StateProgressBar.StateNumber.THREE;
                    }
                    if (currentState.equals("4")) {
                        stateNumber = StateProgressBar.StateNumber.FOUR;
                    }
                    if (stateNumber != null) {

                        holder.stateProgressBar.setCurrentStateNumber(stateNumber);
                        holder.stateProgressBar.setAnimationDuration(3000);
                        holder.stateProgressBar.enableAnimationToCurrentState(true);


                    } else {
                        Log.e("Error", "In current state number null");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /**End update state order
         * */
        final boolean[] isOpen = {false};
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isOpen[0]){
                    holder.linearMoreOptions.setVisibility(View.VISIBLE);
                    isOpen[0] = true;
                }else {
                    holder.linearMoreOptions.setVisibility(View.GONE);
                    isOpen[0] = false;
                }
            }
        });
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("order");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        String stateOrder = dataSnapshot.child(key).child("State Order").getValue(String.class);
                        if(stateOrder != null){
                            if(stateOrder.equals("1") || stateOrder.equals("2")){
                                final SweetAlertDialog dialogCancel = new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE);
                                dialogCancel.setTitle(R.string.are_you_sure_title);
                                String content = view.getContext().getResources().getString(R.string.content_cancel_order) ;
                                dialogCancel.setContentText(content);
                                dialogCancel.setCancelText(view.getContext().getResources().getString(R.string.cancel));
                                dialogCancel.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        databaseReference.child(key).child("Show Again").setValue(false);
                                        String shipper = dataSnapshot.child(key).child("Shipper").getValue(String.class);
                                        //Send notification to shipper already get this order
                                        if(shipper != null) {
                                            String message = getCurrentUser().getFullName() + " " + Resources.getSystem().getString(R.string.notification_shop_canceled_order);
                                            OneSignalHelpers.sendNotification(EncodingFirebase.decodeString(shipper),
                                                    getCurrentUser().getEmail(), message);
                                            String title = "Cancel order";
                                            String keyNotification = key + "_notification_" + getCountNotification(key);
                                            databaseReference.child(key).child("Notifications").child(keyNotification).child("From")
                                                    .setValue(EncodingFirebase.encodeString(getCurrentUser().getEmail()));
                                            DateFormat datetimeFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
                                            String date = datetimeFormat.format(Calendar.getInstance().getTime());
                                            databaseReference.child(key).child("Notifications").child(keyNotification).child("Datetime").setValue(date);
                                            databaseReference.child(key).child("Notifications").child(keyNotification).child("Message").setValue(message);
                                            databaseReference.child(key).child("Notifications").child(keyNotification).child("Title").setValue(title);
                                            databaseReference.child(key).child("Notifications").child(keyNotification).child("Show Again").setValue(true);
                                            dialogCancel.dismissWithAnimation();
                                        }
                                    }
                                });

                                dialogCancel.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogCancel.dismissWithAnimation();
                                    }
                                });
                                dialogCancel.show();
                            }
                            if(stateOrder.equals("3")){
                                final SweetAlertDialog dialogCancel = new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE);
                                dialogCancel.setTitle(R.string.warning);
                                String content = view.getContext().getResources().getString(R.string.warning_state_order_3) ;
                                dialogCancel.setContentText(content);
                                dialogCancel.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogCancel.dismissWithAnimation();
                                    }
                                });
                            }
                            if(stateOrder.equals("4")){
                                final SweetAlertDialog dialogCancel = new SweetAlertDialog(view.getContext(), SweetAlertDialog.WARNING_TYPE);
                                dialogCancel.setTitle(R.string.warning);
                                String content = view.getContext().getResources().getString(R.string.warning_state_order_4) ;
                                dialogCancel.setContentText(content);
                                dialogCancel.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogCancel.dismissWithAnimation();
                                    }
                                });
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TastyToast.makeText(view.getContext(), "aaa",TastyToast.LENGTH_SHORT,TastyToast.CONFUSING);
            }
        });

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailOrderShopActivity.class);
                intent.putExtra("orderKey",key);
                view.getContext().startActivity(intent);
            }
        });

    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(com.minh.findtheshipper.models.RealmObject.User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

    private long getCountNotification(final String key) {
        final long[] result = {0};
        final DatabaseReference databaseNotification = FirebaseDatabase.getInstance().getReference("order/" + key + "/Notifications");
        databaseNotification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                result[0] = dataSnapshot.getChildrenCount();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return result[0];
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
        private LinearLayout linearMoreOptions;
        private CircleButton btnEdit;
        private CircleButton btnDetail;
        private CircleButton btnCancel;



        public ViewHolder(View view) {
            super(view);
            txtStartPlace = view.findViewById(R.id.txtStartPlace);
            txtFinishPlace = view.findViewById(R.id.txtFinishPlace);
            txtShipMoney = view.findViewById(R.id.txtPrice);
            txtDistance = view.findViewById(R.id.txtDistance);
            txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
            txtDatetime = view.findViewById(R.id.txtTimeAgo);
            txtUserName = view.findViewById(R.id.txtUserName);
            userImage = view.findViewById(R.id.imgUserImage);
            ratingBarOrder =  view.findViewById(R.id.ratingOrder);
            stateProgressBar = view.findViewById(R.id.stateProgressBar);
            linearMoreOptions = view.findViewById(R.id.linearMoreOptions);
            btnCancel = view.findViewById(R.id.btnCancelOrder);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDetail = view.findViewById(R.id.btnDetail);
        }
    }
}
