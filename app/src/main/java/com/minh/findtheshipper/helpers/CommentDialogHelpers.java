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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListComment;
import com.minh.findtheshipper.models.CommentTemp;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.minh.findtheshipper.models.RealmObject.User;
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

    @SuppressWarnings("ConstantConditions")
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
            commentList = new ArrayList<>();
            DatabaseReference mDatabaseComment = FirebaseDatabase.getInstance().getReference("order/" + orderID + "/comment");
            mDatabaseComment.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    addItemComment(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateItem(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            Log.e("Error", "Error in loadAllList");
        }
    }

    private void addItemComment(DataSnapshot dataSnapshot) {

        try {

            String content = dataSnapshot.child("Content").getValue(String.class);
            String time = dataSnapshot.child("Time").getValue(String.class);
            String user = dataSnapshot.child("user").getValue(String.class);
            CommentTemp commentTemp = new CommentTemp();
            if (content != null && time != null && user != null) {
                commentTemp.setIdComment(dataSnapshot.getKey());
                commentTemp.setDateTime(time);
                commentTemp.setUserName(user);
                commentTemp.setContent(content);
                commentList.add(commentTemp);
                Collections.sort(commentList, new SortCommentTempHelpers());
                adapter = new CustomAdapterListComment(getActivity(), commentList);
                recyclerView.setAdapter(adapter);
                recyclerView.scrollToPosition(commentList.size() -1);
            }

        } catch (Exception e) {
            Log.e("Error", "Error in loadAllList");
        }
    }

    private void updateItem(DataSnapshot dataSnapshot){

        try {

            String content = dataSnapshot.child("Content").getValue(String.class);
            String time = dataSnapshot.child("Time").getValue(String.class);
            String user = dataSnapshot.child("user").getValue(String.class);
            CommentTemp commentTemp = new CommentTemp();
            if (content != null && time != null && user != null) {
                commentTemp.setIdComment(dataSnapshot.getKey());
                commentTemp.setDateTime(time);
                commentTemp.setUserName(user);
                commentTemp.setContent(content);
                commentList.add(commentTemp);
                Collections.sort(commentList, new SortCommentTempHelpers());
                for(int i = 0 ; i < commentList.size(); i++){
                    if(commentTemp.getIdComment().equals(commentList.get(i).getIdComment())){
                        commentList.remove(i);
                        commentList.add(i, commentTemp);
                        adapter.notifyItemRemoved(i);
                        adapter.notifyItemRangeChanged(i, commentList.size());
                        adapter.notifyDataSetChanged();
                        recyclerView.scrollToPosition(commentList.size() -1);
                        break;
                    }

                }
            }

        } catch (Exception e) {
            Log.e("Error", "Error in loadAllList");
        }
    }

    private User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }

    @SuppressWarnings("ConstantConditions")
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
