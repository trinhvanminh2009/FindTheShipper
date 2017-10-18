package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.NotificationObject;

import java.util.List;

/**
 * Created by trinh on 6/16/2017.
 */

public class CustomAdapterListViewNotification extends RecyclerView.Adapter<CustomAdapterListViewNotification.ViewHolder> {
    private Context context;
    private List<NotificationObject> notificationObjectList;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notification,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNotification;
        private ImageView imgNotification;
        public ViewHolder(View itemView) {
            super(itemView);
            txtNotification = itemView.findViewById(R.id.txtNotification);
            imgNotification = itemView.findViewById(R.id.imgNotification);
        }
    }
}
