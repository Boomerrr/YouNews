package com.example.think.younews.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.younews.Bean.UserNews;
import com.example.think.younews.R;

import java.util.List;

public class UserNewsAdapter extends RecyclerView.Adapter<UserNewsAdapter.ViewHolder> implements View.OnClickListener{
    private List<UserNews> userNewsList;
    private OnItemClickListener mItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView new_text;
        ImageView user_head;
        TextView user_name;
        TextView user_time;
        public ViewHolder(View view){
            super(view);
            new_text = (TextView) view.findViewById(R.id.new_text);
            user_head = (ImageView) view.findViewById(R.id.user_head);
            user_name = (TextView) view.findViewById(R.id.user_name);
            user_time = (TextView) view.findViewById(R.id.user_time);
        }
    }
    public UserNewsAdapter(List<UserNews> userNewsList){
        this.userNewsList = userNewsList;
    }
    @NonNull
    @Override
    public UserNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_usernews,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserNewsAdapter.ViewHolder viewHolder, int i) {
        UserNews userNews = userNewsList.get(i);
        viewHolder.user_head.setImageResource(R.drawable.blue);
        viewHolder.new_text.setText(userNews.getText());
        viewHolder.user_name.setText(userNews.getUserName());
        viewHolder.user_time.setText(userNews.getTime());
        viewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return userNewsList.size();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
