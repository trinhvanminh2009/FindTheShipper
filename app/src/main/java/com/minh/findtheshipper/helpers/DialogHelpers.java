package com.minh.findtheshipper.helpers;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Adapters.CustomAdapterListviewNotification;
import com.minh.findtheshipper.models.NotificationObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by trinh on 6/16/2017.
 */

public class DialogHelpers extends DialogFragment implements DialogInterface.OnClickListener {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment,container,false);
        ListView listView = (ListView)view.findViewById(R.id.listNotification);
        ArrayList<NotificationObject> notificationObjectList = new ArrayList<>();
        notificationObjectList.add(new NotificationObject(R.mipmap.ic_launcher_app,getResources().getString( R.string.choosing_finish_point)) );
        getDialog().setTitle("Notification");
        CustomAdapterListviewNotification customAdapterListviewNotification = new CustomAdapterListviewNotification(getActivity(),notificationObjectList);
        listView.setAdapter(customAdapterListviewNotification);
        return view;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }


}
