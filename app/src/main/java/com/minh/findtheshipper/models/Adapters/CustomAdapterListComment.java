package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.minh.findtheshipper.EncodingFirebase;
import com.minh.findtheshipper.R;
import com.minh.findtheshipper.helpers.TimeAgoHelpers;
import com.minh.findtheshipper.models.CommentTemp;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

/**
 * Created by trinh on 6/26/2017.
 */

public class CustomAdapterListComment extends RecyclerView.Adapter<CustomAdapterListComment.ViewHolder> {
    private List<CommentTemp> commentList;
    private Context context;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comments,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public CustomAdapterListComment(Context context, List<CommentTemp> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(commentList.size() >0)
        {
            /**
             * Get avatar and name of user from id comment in server.
             * Using library Glide to get image from url after decode url from server.
             */

            final CommentTemp comment = commentList.get(position);
            final EncodingFirebase encodingFirebase = new EncodingFirebase();
            TimeAgoHelpers timeAgoHelpers = new TimeAgoHelpers();
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String key = encodingFirebase.getEmailFromUserID(comment.getIdComment());
                    String url = dataSnapshot.child(key).child("Avatar").getValue(String.class);
                    String name = dataSnapshot.child(key).child("Name").getValue(String.class);
                    holder.txtUserName.setText(name);
                    Glide.with(context).load(encodingFirebase.decodeString(url)).apply(RequestOptions.circleCropTransform()).thumbnail(0.5f).into(holder.imgAvatar);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            holder.txtContent.setText(comment.getContent());
            holder.txtDateTime.setText(timeAgoHelpers.getTimeAgo(comment.getDateTime(), context) );

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
