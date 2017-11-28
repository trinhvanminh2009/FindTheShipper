package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.NotificationObject;
import com.minh.findtheshipper.models.RealmObject.NotificationData;

import java.util.List;

import io.realm.Realm;

/**
 * Created by trinh on 6/16/2017.
 * Notification for Shipper and Shop
 */

public class CustomAdapterListViewNotification extends RecyclerView.Adapter<CustomAdapterListViewNotification.ViewHolder> {
    private Context context;
    private Realm realm;
    private List<NotificationObject> notificationObjectList;

    public CustomAdapterListViewNotification(Context context) {
        this.context = context;
    }

    public CustomAdapterListViewNotification(Context context, List<NotificationObject> notificationObjectList) {
        this.context = context;
        this.notificationObjectList = notificationObjectList;
    }
    public void initRealm() {
        realm = null;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notification,parent,false);
        Realm.init(view.getContext());
        initRealm();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (notificationObjectList.size() > 0){
            NotificationObject notificationObject = notificationObjectList.get(position);
            if(notificationObject.getTitle() != null && notificationObject.getContent() != null
                    && notificationObject.getDateTime() != null){
                holder.txtDateTime.setText(TimeAgoHelpers.getTimeAgo(notificationObject.getDateTime(),context));
                holder.txtNotificationTitle.setText(notificationObject.getTitle());
                holder.txtNotificationContent.setText(notificationObject.getContent());
                realm.beginTransaction();
                getNotificationData().setTotalNotification(notificationObjectList.size());
                getNotificationData().setNumberUnread(notificationObjectList.size());
                realm.commitTransaction();

            }

        }
        holder.itemView.setClickable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    private NotificationData getNotificationData(){
        return realm.where(NotificationData.class).findFirst();
    }


    @Override
    public int getItemCount() {
        return notificationObjectList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNotificationTitle;
        private TextView txtNotificationContent;
        private TextView txtDateTime;
        private ImageView imgNotification;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNotificationTitle = itemView.findViewById(R.id.txtNotificationTitle);
            txtNotificationContent = itemView.findViewById(R.id.txtNotificationContent);
            txtDateTime = itemView.findViewById(R.id.txtDateTime);
            imgNotification = itemView.findViewById(R.id.imgNotification);

        }
    }
}
