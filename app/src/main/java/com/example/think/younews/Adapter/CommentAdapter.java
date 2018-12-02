package com.example.think.younews.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.younews.Bean.Comment;
import com.example.think.younews.R;

import java.util.List;
import java.util.Random;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>implements View.OnClickListener{
    private List<Comment> commentList;
    private int[] image = {R.drawable.club5,R.drawable.club6,R.drawable.club7,R.drawable.club8};
    private OnItemClickListener mItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView new_iamge;
        TextView new_text;
        ImageView user_head;
        TextView user_name;
        TextView user_time;
        public ViewHolder(View view){
            super(view);
            new_iamge = (ImageView) view.findViewById(R.id.new_image);
            new_text = (TextView) view.findViewById(R.id.new_text);
            user_head = (ImageView) view.findViewById(R.id.user_head);
            user_name = (TextView) view.findViewById(R.id.user_name);
            user_time = (TextView) view.findViewById(R.id.user_time);
        }
    }
    public CommentAdapter(List<Comment> commentList){
        this.commentList = commentList;
    }
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder viewHolder, int i) {
        Random random = new Random();
        int j = random.nextInt(7);
        Comment comment = commentList.get(i);
        viewHolder.user_time.setText(comment.getTime());
        viewHolder.user_name.setText(comment.getUserName());
        viewHolder.new_text.setText(comment.getComment());
        viewHolder.user_head.setImageResource(R.drawable.blue);
        viewHolder.new_iamge.setImageResource(image[j]);
    }

    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}