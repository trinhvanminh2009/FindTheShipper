package com.minh.findtheshipper.models.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minh.findtheshipper.R;
import com.minh.findtheshipper.models.CommentTemp;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(commentList.size() >0)
        {
            //  URL imageURL = new URL("https://graph.facebook.com/211261632726746/picture?width=200&height=200");
            //  Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
            CommentTemp comment = commentList.get(position);
            holder.imgAvatar.setImageResource(R.drawable.ic_about);
            holder.txtUserName.setText(comment.getUserName());
            holder.txtContent.setText(comment.getContent());
            holder.txtDateTime.setText(comment.getDateTime());

         /*   holder.imgAvatar.setImageResource(comment.getUser().getAvatar());
            holder.txtUserName.setText(comment.getUser().getFullName());
            holder.txtContent.setText(comment.getContent());
            holder.txtDateTime.setText(comment.getDateTime());*/
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
