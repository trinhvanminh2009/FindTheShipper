package com.minh.findtheshipper.models.Adapters;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.NotificationObject;

import java.util.List;

/**
 * Created by trinh on 6/16/2017.
 */

public class CustomAdapterListviewNotification extends ArrayAdapter<NotificationObject> {
    private Context context;
    private List<NotificationObject> notificationObjectList;

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<NotificationObject> getNotificationObjectList() {
        return notificationObjectList;
    }

    public void setNotificationObjectList(List<NotificationObject> notificationObjectList) {
        this.notificationObjectList = notificationObjectList;
    }

    public CustomAdapterListviewNotification(@NonNull Context context, @NonNull List<NotificationObject> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_notification, parent,false);
        ImageView imgNotification = (ImageView)view.findViewById(R.id.imgNotification);
        TextView txtNotification = (TextView)view.findViewById(R.id.txtNotification);
        NotificationObject notificationObject = notificationObjectList.get(position);
        imgNotification.setImageResource(notificationObject.getIcon());
        txtNotification.setText(notificationObject.getContext());
        return view;
    }
}
