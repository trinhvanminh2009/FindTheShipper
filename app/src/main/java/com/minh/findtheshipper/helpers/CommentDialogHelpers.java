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
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListComment;
import com.minh.findtheshipper.models.CommentTemp;
import com.minh.findtheshipper.models.CurrentUser;
import com.minh.findtheshipper.models.User;
import com.sdsmdg.tastytoast.TastyToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by trinh on 6/27/2017.
 * Handle comment
 */

public class CommentDialogHelpers extends DialogFragment {
    private Realm realm;
    private ArrayList<CommentTemp> commentList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String orderID;
    private EditText editComment;
    ImageButton btnComment;
    RecyclerView.LayoutManager layoutManager;
    private long countComment[] = new long[2];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().getAttributes().windowAnimations = R.style.MyDialogAnimation;
        View view = inflater.inflate(R.layout.dialog_comment, container, false);
        ButterKnife.bind(getActivity());
        recyclerView = view.findViewById(R.id.listComment);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Realm.init(getActivity());
        initRealm();
        Toolbar toolbar = view.findViewById(R.id.toolBar);
        toolbar.setTitle(getResources().getString(R.string.comment));
        editComment = view.findViewById(R.id.editComment);
        btnComment = view.findViewById(R.id.btnSendComment);
        orderID = getArguments().getString("orderID");//From button send comment in DetailOrderShipperActivity
        loadAllList();
        DatabaseReference mDatabaseComment = FirebaseDatabase.getInstance().getReference("order/" + orderID + "/comment");

        mDatabaseComment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                countComment[0] = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!editComment.getText().toString().trim().matches("")) {
                    insertComment();
                    editComment.setText("");
                    // loadAllList();
                } else {
                    TastyToast.makeText(getActivity(), "Please type a comment", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                }
            }
        });
        return view;
    }

    private void insertComment() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
        String idComment = "cmt_" + EncodingFirebase.encodeString(getCurrentUser().getEmail()) + "_" + orderID + "_" + countComment[0];
        mDatabase.child(orderID).child("comment").child(idComment).child("user").setValue(getCurrentUser().getEmail());
        mDatabase.child(orderID).child("comment").child(idComment).child("Content").setValue(editComment.getText().toString());
        mDatabase.child(orderID).child("comment").child(idComment).child("Time").setValue(getCurrentTime());
       /* final Comment[] comment = new Comment[1];
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Order order = realm.where(Order.class).equalTo("orderID",orderID).findFirst();
                comment[0] = realm.createObject(Comment.class,"cmt_"+getCurrentUser().getEmail()+"_"+order.getOrderID()+"_"+countOrder());
                comment[0].setContent(editComment.getText().toString());
                comment[0].setDateTime(getCurrentTime());
                comment[0].setUser(getCurrentUser());
                realm.insertOrUpdate(comment[0]);
            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Order order = realm.where(Order.class).equalTo("orderID",orderID).findFirst();
                order.getComments().add(comment[0]);
                realm.insertOrUpdate(order);
                //Add comment into server
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
                mDatabase.child(order.getOrderID()).child("comment").child(comment[0].getIdComment()).child("user").setValue(comment[0].getUser().getEmail());
                mDatabase.child(order.getOrderID()).child("comment").child(comment[0].getIdComment()).child("Content").setValue(comment[0].getContent());
                mDatabase.child(order.getOrderID()).child("comment").child(comment[0].getIdComment()).child("Time").setValue(comment[0].getDateTime());

            }
        });
*/
    }

    public String getCurrentTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }


    public void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    public void loadAllList() {
        try {
            DatabaseReference mDatabaseComment = FirebaseDatabase.getInstance().getReference("order/" + orderID + "/comment");
            mDatabaseComment.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    commentList = new ArrayList<>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        final String key = child.getKey();
                        String content = dataSnapshot.child(key).child("Content").getValue(String.class);
                        String time = dataSnapshot.child(key).child("Time").getValue(String.class);
                        String user = dataSnapshot.child(key).child("user").getValue(String.class);
                        CommentTemp commentTemp = new CommentTemp();
                        commentTemp.setIdComment(key);
                        commentTemp.setDateTime(time);
                        commentTemp.setUserName(user);
                        commentTemp.setContent(content);
                        commentList.add(commentTemp);
                    }
                    try {
                        Collections.sort(commentList, new SortCommentTempHelpers());
                        adapter = new CustomAdapterListComment(getActivity(), commentList);
                        recyclerView.setAdapter(adapter);
                    } catch (Exception e) {
                        Log.e("Error", "Error in loadAllList");
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } catch (Exception e) {
            Log.e("Error", "Error in loadAllList");
        }
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return  realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.comment_width);
        int height = getResources().getDimensionPixelSize(R.dimen.comment_height);
        getDialog().getWindow().setLayout(width, height);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }


}
