package com.minh.findtheshipper.helpers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListViewNotification;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListViewOrderItem;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.NotificationObject;
import com.minh.findtheshipper.models.OrderTemp;
import com.minh.findtheshipper.models.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class DialogHelpers extends DialogFragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<NotificationObject> notificationObjectArrayList;
    private CustomAdapterListViewNotification customAdapterListviewNotification;
    private Realm realm;
    RecyclerView.LayoutManager layoutManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().getAttributes().windowAnimations = R.style.MyDialogAnimation;
        View view = inflater.inflate(R.layout.list_recycle_view, container, false);
        initRealm();
        recyclerView = view.findViewById(R.id.recycle_view);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadNotification();
        return view;
    }

    public void initRealm() {
        Realm.init(getActivity());
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    public void loadNotification() {
        DatabaseReference dataNotification = FirebaseDatabase.getInstance().getReference();
        dataNotification.child("order").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot order :dataSnapshot.getChildren()) {
                    Log.e("Error","In here");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        String startAtKey = "order_" + EncodingFirebase.encodeString(getCurrentUser().getEmail());
        //Query to get notification in orders is allow showing.
        Query queryNotification = dataNotification.orderByChild("order")
                .startAt(true, startAtKey).endAt(true, startAtKey).orderByChild("Notifications")
                .orderByChild("Show Again").equalTo(true);
        queryNotification.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notificationObjectArrayList = new ArrayList<>();
                for (DataSnapshot order : dataSnapshot.getChildren()) {
                    String key = order.getKey();
                    String title = dataSnapshot.child(key).child("Title").getValue(String.class);
                    String message = dataSnapshot.child(key).child("Message").getValue(String.class);
                    String dateTime = dataSnapshot.child(key).child("Datetime").getValue(String.class);
                    NotificationObject notificationObject = new NotificationObject();
                    notificationObject.setIcon(R.mipmap.ic_launcher_app);
                    notificationObject.setContent(message);
                    notificationObject.setTitle(title);
                    notificationObject.setDateTime(dateTime);
                    notificationObjectArrayList.add(notificationObject);

                }
                adapter = new CustomAdapterListViewNotification(getContext(), notificationObjectArrayList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.dialog_height);
        getDialog().getWindow().setLayout(width, height);
        /*Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        */

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
