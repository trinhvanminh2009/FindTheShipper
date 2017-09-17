package com.minh.findtheshipper.models.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.helpers.EncodingFireBase;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.CommentDialogHelpers;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.User;
import com.minh.findtheshipper.utils.AnimationUtils;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by trinh on 6/22/2017.
 */

public class CustomAdapterListviewOrderShipper extends RecyclerView.Adapter<CustomAdapterListviewOrderShipper.ViewHolder>{
    private List<OrderTemp> orderList;
    private Context context;
    private Realm realm;

    private int previousPosition = -1;
    public CustomAdapterListviewOrderShipper(Context context ,List<OrderTemp> orderList) {
        this.context = context;
        this.orderList = orderList;

    }
    @Override
    public CustomAdapterListviewOrderShipper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_shipper,parent,false);
        realm.init(view.getContext());
        initRealm();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CustomAdapterListviewOrderShipper.ViewHolder holder, int position) {
        final AnimationUtils animationUtils = new AnimationUtils();
        final OrderTemp order = orderList.get(position);
        /**Query name from FireBase using id in orders*/
        final EncodingFireBase encodingFireBase = new EncodingFireBase();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
        final DatabaseReference orderDatabase = FirebaseDatabase.getInstance().getReference("order");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String key = encodingFireBase.getEmailFromUserID(order.getOrderID());
                String nameCreator = dataSnapshot.child(key).child("Name").getValue(String.class);
                holder.nameCreator.setText(nameCreator);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        TimeAgoHelpers timeAgoHelpers = new TimeAgoHelpers();
        String timeAgo = timeAgoHelpers.getTimeAgo(order.getDateTime(),context);
        holder.startingPoint.setText(order.getStartPoint());
        holder.finishPoint.setText(order.getFinishPoint());
        holder.advancedMoney.setText(order.getAdvancedMoney());
        holder.shipMoney.setText(order.getShipMoney());
        holder.note.setText(order.getNote());
        holder.phoneNumber.setText(order.getPhoneNumber());
        holder.txtTimeAgo.setText(timeAgo);
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


        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("orderID",order.getOrderID());
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                final CommentDialogHelpers dialogHelpers = new CommentDialogHelpers();
                dialogHelpers.show(fragmentManager,"New fragment");
                dialogHelpers.setArguments(bundle);

            }
        });

        holder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Order orderRealm = realm.where(Order.class).equalTo("orderID",order.getOrderID()).findFirst();
                if(orderRealm == null)//Check it available in database
                {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            final Order orderToRealm = realm.createObject(Order.class,order.getOrderID());
                            orderToRealm.setStatus(order.getStatus());
                            orderToRealm.setStartPoint(order.getStartPoint());
                            orderToRealm.setFinishPoint(order.getFinishPoint());
                            orderToRealm.setAdvancedMoney(order.getAdvancedMoney());
                            orderToRealm.setShipMoney(order.getShipMoney());
                            orderToRealm.setNote(order.getNote());
                            orderToRealm.setSaveOrder(order.getSavedOrder());
                            orderToRealm.setPhoneNumber(order.getPhoneNumber());
                            realm.insertOrUpdate(orderToRealm);
                            getCurrentUser().getOrderListSave().add(orderToRealm);
                            realm.insertOrUpdate(getCurrentUser());
                            if(!orderToRealm.getSaveOrder()) //Check order is false exists before handle
                            {
                                animationUtils.animateItem(holder);

                                //Check it already exists in list order save of user
                                boolean checkAlready = false;
                                User user = getCurrentUser();
                                RealmList<Order> orders = user.getOrderListSave();
                                for(int i = 0 ; i < orders.size(); i++)
                                {
                                    if(orders.get(i).getOrderID().equals(orderToRealm.getOrderID()))
                                    {
                                        checkAlready = true;
                                    }
                                }
                                if (!checkAlready)
                                {
                                    orderToRealm.setSaveOrder(true);
                                    realm.insertOrUpdate(orderToRealm);
                                    user.getOrderListSave().add(orderToRealm);
                                    realm.insertOrUpdate(user);
                                    animationUtils.animateItem(holder);
                                    TastyToast.makeText(v.getContext(),v.getResources().getString(R.string.save_order) ,TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);

                                }
                                if (checkAlready)
                                {
                                    orderToRealm.setSaveOrder(true);
                                    realm.insertOrUpdate(orderToRealm);
                                    TastyToast.makeText(v.getContext(),v.getResources().getString(R.string.save_order) ,TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);

                                }
                            }
                            else {
                                animationUtils.animateItem(holder);
                                TastyToast.makeText(v.getContext(),v.getResources().getString(R.string.save_order_exists) ,TastyToast.LENGTH_SHORT,TastyToast.WARNING);
                            }
                        }
                    });

                }
                else if(orderRealm != null)
                {
                    if(!orderRealm.getSaveOrder()) //Check order is false exists before handle
                    {
                        animationUtils.animateItem(holder);
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                //Check it already exists in list order save of user
                                boolean checkAlready = false;
                                User user = getCurrentUser();
                                RealmList<Order> orders = user.getOrderListSave();
                                for(int i = 0 ; i < orders.size(); i++)
                                {
                                    if(orders.get(i).getOrderID().equals(order.getOrderID()))
                                    {
                                        checkAlready = true;
                                    }
                                }
                                if (!checkAlready)
                                {
                                    orderRealm.setSaveOrder(true);
                                    realm.insertOrUpdate(orderRealm);
                                    user.getOrderListSave().add(orderRealm);
                                    realm.insertOrUpdate(user);
                                    animationUtils.animateItem(holder);
                                    TastyToast.makeText(v.getContext(),v.getResources().getString(R.string.save_order) ,TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);

                                }
                                if (checkAlready)
                                {
                                    orderRealm.setSaveOrder(true);
                                    realm.insertOrUpdate(orderRealm);
                                    TastyToast.makeText(v.getContext(),v.getResources().getString(R.string.save_order) ,TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                                }
                            }
                        });
                    }
                    else {
                        animationUtils.animateItem(holder);
                        TastyToast.makeText(v.getContext(),v.getResources().getString(R.string.save_order_exists) ,TastyToast.LENGTH_SHORT,TastyToast.WARNING);
                    }
                }
            }
        });

        holder.btnGetOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderDatabase.child(order.getOrderID()).child("User Get Order").setValue(encodingFireBase.encodeString(getCurrentUser().getEmail()));

            }
        });
    }

    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }
    private User getCurrentUser()
    {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        User user = realm.where(User.class).beginGroup().equalTo("email",currentUser.getEmail()).endGroup().findFirst();
        return user;
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameCreator;
        private TextView startingPoint;
        private TextView finishPoint;
        private TextView advancedMoney;
        private TextView shipMoney;
        private TextView note;
        private TextView phoneNumber;
        private Button btnComment;
        private Button btnCall;
        private Button btnSave;
        private Button btnGetOrder;
        private TextView txtTimeAgo;

        public ViewHolder(final View view) {
            super(view);
            startingPoint = (TextView) view.findViewById(R.id.txtStatingPointShipper);
            finishPoint = (TextView) view.findViewById(R.id.txtFinishPointShipper);
            advancedMoney = (TextView) view.findViewById(R.id.txtAdvancedMoneyShipper);
            shipMoney = (TextView) view.findViewById(R.id.txtShipMoneyShipper);
            note = (TextView) view.findViewById(R.id.txtNoteShipper);
            phoneNumber = (TextView) view.findViewById(R.id.txtPhoneNumberShipper);
            btnComment = (Button) view.findViewById(R.id.btnComment);
            btnCall = (Button) view.findViewById(R.id.btnCall);
            btnSave = (Button) view.findViewById(R.id.btnSave);
            btnGetOrder = (Button)view.findViewById(R.id.btnGetOrder);
            nameCreator = (TextView)view.findViewById(R.id.nameCreator);
            txtTimeAgo = (TextView)view.findViewById(R.id.txtTimeAgo);

        }
    }

}
