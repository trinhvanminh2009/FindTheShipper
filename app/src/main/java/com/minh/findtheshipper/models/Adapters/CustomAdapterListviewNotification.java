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

import java.util.List;

/**
 * Created by trinh on 6/16/2017.
 *
 */

public class CustomAdapterListViewNotification extends RecyclerView.Adapter<CustomAdapterListViewNotification.ViewHolder> {
    private Context context;
    private List<NotificationObject> notificationObjectList;


    public CustomAdapterListViewNotification(Context context, List<NotificationObject> notificationObjectList) {
        this.context = context;
        this.notificationObjectList = notificationObjectList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notification,parent,false);
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
            }

        }

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
