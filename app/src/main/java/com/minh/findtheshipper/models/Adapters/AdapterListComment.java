package com.minh.findtheshipper.models.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.Comment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by trinh on 6/26/2017.
 */

public class AdapterListComment extends RecyclerView.Adapter<AdapterListComment.ViewHolder> {
    private List<Comment> commentList;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public AdapterListComment(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(commentList.size() >0)
        {
            Comment comment = commentList.get(position);
            holder.imgAvatar.setImageResource(comment.getUser().getAvatar());
            holder.txtUserName.setText(comment.getUser().getFullName());
            holder.txtContent.setText(comment.getContent());
            holder.txtDateTime.setText(comment.getDateTime());
        }

    }



    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView txtUserName;
        private TextView txtContent;
        private TextView txtDateTime;
        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = (ImageView)itemView.findViewById(R.id.imgAvatarComment);
            txtUserName = (TextView)itemView.findViewById(R.id.txtUserNameComment);
            txtContent = (TextView)itemView.findViewById(R.id.txtContentComment);
            txtDateTime = (TextView)itemView.findViewById(R.id.txtDatetimeComment);
        }
    }
}
