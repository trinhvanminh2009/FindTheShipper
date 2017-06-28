package com.minh.findtheshipper.helpers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.TimeUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.AdapterListComment;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewOrderShipper;
import com.minh.findtheshipper.models.Comment;
import com.minh.findtheshipper.models.Order;
import com.minh.findtheshipper.models.User;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by trinh on 6/27/2017.
 */

public class CommentDialogHelpers extends DialogFragment {
    private Realm realm;
    private ArrayList<Comment>commentList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String orderID;
    private  EditText editComment;
    private  Button btnComment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_comment, container,false);
        ButterKnife.bind(getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.listComment);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        realm.init(getActivity());
        initRealm();
        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolBar);
        toolbar.setTitle(getResources().getString(R.string.comment));
        editComment = (EditText)view.findViewById(R.id.editComment);
        btnComment = (Button)view.findViewById(R.id.btnSendComment);
        orderID = getArguments().getString("orderID");
        loadAllList();

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editComment.getText().toString().trim().matches(""))
                {
                    insertComment();
                    editComment.setText("");
                    loadAllList();
                }
                else {
                    TastyToast.makeText(getActivity(),"Please type a comment",TastyToast.LENGTH_SHORT,TastyToast.WARNING);
                }
            }
        });
        return view;
    }

    private void insertComment()
    {
        final Comment[] comment = new Comment[1];
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                comment[0] = realm.createObject(Comment.class,"cmt"+countOrder());
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
            }
        });

    }

    public String getCurrentTime()
    {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm");
        return simpleDateFormat.format(new Date());
    }

    public long countOrder()
    {
        return realm.where(Comment.class).count();
    }

    public void initRealm()
    {
        realm = null;
        realm = Realm.getDefaultInstance();
    }
    public void loadAllList()
    {
        try
        {
            commentList = new ArrayList<>();
            Order order = realm.where(Order.class).equalTo("orderID",orderID).findFirst();
            //Get RealmResults from object is RealmList
            RealmResults<Comment>commentRealmResults = order.getComments().where().findAllSorted("dateTime",Sort.DESCENDING);
            for (int i = 0; i <commentRealmResults.size(); i++)
            {
                commentList.add(commentRealmResults.get(i));
            }
            adapter = new AdapterListComment(commentList);
            recyclerView.setAdapter(adapter);
        }catch (Exception e)
        {
        }
    }
    private User getCurrentUser()
    {
        User user = realm.where(User.class).beginGroup().equalTo("email","trinhvanminh2009").endGroup().findFirst();
        return user;
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.comment_width);
        int height = getResources().getDimensionPixelSize(R.dimen.comment_height);
        getDialog().getWindow().setLayout(width,height);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }




}
