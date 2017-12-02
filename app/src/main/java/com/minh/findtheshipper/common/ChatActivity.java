package com.minh.findtheshipper.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.bassaer.chatmessageview.model.User;
import com.github.bassaer.chatmessageview.models.Message;
import com.github.bassaer.chatmessageview.views.ChatView;
import com.github.bassaer.chatmessageview.views.MessageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.EncodingFirebase;
import com.minh.findtheshipper.models.RealmObject.CurrentUser;
import com.sdsmdg.tastytoast.TastyToast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;

import io.realm.Realm;

public class ChatActivity extends AppCompatActivity {

    private ChatView mChatView;
    private String orderKey;
    private ArrayList<Message> commentListSend;
    private ArrayList<Message> commentListReceive;
    private ArrayList<Message> currentCommentListSend;
    private ArrayList<Message> currentCommentListReceive;

    private Realm realm;
    private User me;
    private User you;
    private long countComment[] = new long[2];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initRealm();
        //noinspection ConstantConditions
        orderKey = (String) getIntent().getExtras().get("orderID");//From activities using comment
        commentListSend = new ArrayList<>();
        commentListReceive = new ArrayList<>();
        currentCommentListReceive = new ArrayList<>();
        currentCommentListSend = new ArrayList<>();
        DatabaseReference mDatabaseComment = FirebaseDatabase.getInstance().getReference("order/" + orderKey + "/comment");
        mDatabaseComment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                countComment[0] = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //User id
        int myId = 0;
        //User icon
        Bitmap myIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_2);
        //User name
        String myName = getCurrentUser().getFullName();

        int yourId = 1;
        Bitmap yourIcon = BitmapFactory.decodeResource(getResources(), R.drawable.face_1);
        String yourName = "Emily";

        me = new User(myId, myName, myIcon);
        you = new User(yourId, yourName, yourIcon);

        mChatView = findViewById(R.id.chat_view);
        loadAllList();
        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.blueGray500));
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.red500));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setInputTextHint("New comment...");
        mChatView.setMessageMarginTop(5);
        mChatView.setMessageMarginBottom(5);
        mChatView.setAutoScroll(true);
        mChatView.setAutoHidingKeyboard(true);


        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Add new comment into server
                insertComment();
                mChatView.setInputText("");
                mChatView.setInputTextHint("New comment...");
                //mChatView.setAutoScroll(true);
            }

        });

        final MessageView messageView = mChatView.getMessageView();
        messageView.setOnKeyboardAppearListener(new MessageView.OnKeyboardAppearListener(){
            @Override
            public void onKeyboardAppeared(boolean hasChanged) {
                //Appeared keyboard
                if (hasChanged) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            //Scroll to end
                            //messageView.scrollToEnd();
                            Toast.makeText(ChatActivity.this, "kappears", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(ChatActivity.this, "no change", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    private void insertComment() {
        commentListReceive.clear();
        commentListSend.clear();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("order");
        String idComment = "cmt_" + EncodingFirebase.encodeString(getCurrentUser().getEmail()) + "_" + orderKey + "_" + countComment[0];
        mDatabase.child(orderKey).child("comment").child(idComment).child("user").setValue(getCurrentUser().getEmail());
        mDatabase.child(orderKey).child("comment").child(idComment).child("Content").setValue(mChatView.getInputText());
        mDatabase.child(orderKey).child("comment").child(idComment).child("Time").setValue(getCurrentTime());
    }

    private String getCurrentTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    private void loadAllList() {
        try {
            DatabaseReference mDatabaseComment = FirebaseDatabase.getInstance().getReference("order/" + orderKey + "/comment");

            mDatabaseComment.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot child : dataSnapshot.getChildren()) {

                        final String key = child.getKey();
                        String content = dataSnapshot.child(key).child("Content").getValue(String.class);
                        String time = dataSnapshot.child(key).child("Time").getValue(String.class);
                        String user = dataSnapshot.child(key).child("user").getValue(String.class);
                        //CommentTemp commentTemp = new CommentTemp();
                        Message messageSend = null;
                        Message messageReceive = null;
                        if(content != null && time != null && user != null){
                            //Check if those messages of this user
                            if (user.equals(getCurrentUser().getEmail())) {
                                try {

                                    messageSend = new Message.Builder().setUser(me)
                                            .setCreatedAt(EncodingFirebase.convertDateToCalendar(time))
                                            .setRightMessage(true).setMessageText(content).build();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (messageSend != null) {
                                    commentListSend.add(messageSend);
                                }

                            } else {
                                try {
                                    //Those messages not of this user using this device
                                    you.setName(EncodingFirebase.getNameOfUser(EncodingFirebase.encodeString(user)));
                                    messageReceive = new Message.Builder().setUser(you)
                                            .setCreatedAt(EncodingFirebase.convertDateToCalendar(time))
                                            .setRightMessage(false).setMessageText(content).build();
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (messageReceive != null) {
                                    commentListReceive.add(messageReceive);
                                }
                            }
                        }

                    }

                    commentListSend = new ArrayList<>(new LinkedHashSet<>(commentListSend));
                    commentListReceive = new ArrayList<>(new LinkedHashSet<>(commentListReceive));
                    TastyToast.makeText(getApplicationContext() , "size:"+ commentListSend.size(), TastyToast.LENGTH_SHORT,TastyToast.CONFUSING);

                    for (int i = 0; i < commentListSend.size(); i++) {
                        mChatView.send(commentListSend.get(i));
                    }

                    for (int i = 0; i < commentListReceive.size(); i++) {
                        mChatView.receive(commentListReceive.get(i));
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } catch (Exception e) {
            Log.e("Error", "Error in loadAllList");
        }
        //Remove duplicated element


    }

    private com.minh.findtheshipper.models.RealmObject.User getCurrentUser() {
        CurrentUser currentUser = realm.where(CurrentUser.class).findFirst();
        return realm.where(com.minh.findtheshipper.models.RealmObject.User.class).beginGroup().equalTo("email", currentUser.getEmail()).endGroup().findFirst();
    }
}
