package com.minh.findtheshipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.models.Comment;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.User;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by trinh on 6/16/2017.
 */

public class Test extends BaseActivity {

    @BindView(R.id.toolBar) Toolbar toolbar;
    private TextView textView;
    private Realm realm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        textView = (TextView)findViewById(R.id.testFireBase);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test");
        NavigationDrawer(toolbar);
        realm.init(this);
        initRealm();
        RetrievingData();

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_fragment;
    }
    @Override
    protected void onStart() {
        super.onStart();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Order> orders = realm.where(Order.class).findAll();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
                for(int i = 0 ; i < orders.size(); i++)
                {
                    mDatabase.child(orders.get(i).getOrderID()).child("Status").setValue(orders.get(i).getStatus());
                    mDatabase.child(orders.get(i).getOrderID()).child("Start place").setValue(orders.get(i).getStartPoint());
                    mDatabase.child(orders.get(i).getOrderID()).child("Finish place").setValue(orders.get(i).getFinishPoint());
                    mDatabase.child(orders.get(i).getOrderID()).child("Advanced money").setValue(orders.get(i).getAdvancedMoney());
                    mDatabase.child(orders.get(i).getOrderID()).child("Phone number").setValue(orders.get(i).getPhoneNumber());
                    mDatabase.child(orders.get(i).getOrderID()).child("Ship Money").setValue(orders.get(i).getShipMoney());
                    mDatabase.child(orders.get(i).getOrderID()).child("Note").setValue(orders.get(i).getNote());
                    mDatabase.child(orders.get(i).getOrderID()).child("Distance").setValue(orders.get(i).getDistance());
                    mDatabase.child(orders.get(i).getOrderID()).child("Datetime").setValue(orders.get(i).getDateTime());
                    mDatabase.child(orders.get(i).getOrderID()).child("Save Order").setValue(orders.get(i).getSaveOrder());
                    RealmList<Comment>comments = orders.get(i).getComments();
                    if(comments.size() > 0)
                    {
                        for(int j = 0; j< comments.size(); j++)
                        {
                            mDatabase.child(orders.get(i).getOrderID()).child("comment").child(comments.get(j).getIdComment()).child("user").setValue(comments.get(j).getUser().getEmail());
                            mDatabase.child(orders.get(i).getOrderID()).child("comment").child(comments.get(j).getIdComment()).child("Content").setValue(comments.get(j).getContent());
                            mDatabase.child(orders.get(i).getOrderID()).child("comment").child(comments.get(j).getIdComment()).child("Time").setValue(comments.get(j).getDateTime());

                        }
                    }

                }
            }
        });

    }

    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void RetrievingData()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Order> orders = realm.where(Order.class).findAll();
                for(int i =0; i< orders.size(); i++)
                {
                    final DatabaseReference ref = database.getReference("order/"+orders.get(i).getOrderID());
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String text = dataSnapshot.child("Start place").getValue(String.class);
                            TastyToast.makeText(Test.this, text, TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });



    }
}
