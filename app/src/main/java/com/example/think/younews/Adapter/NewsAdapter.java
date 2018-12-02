package com.example.think.younews.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.younews.Bean.News;
import com.example.think.younews.R;

import java.util.List;
import java.util.Random;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>implements View.OnClickListener{
    private OnItemClickListener mItemClickListener;
    private List<News> newsList;
    private int[] image = {R.drawable.club1,R.drawable.club2,R.drawable.club3,R.drawable.club4};
    @Override
    public void onClick(View v) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }
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
    public NewsAdapter(List<News> newsList){
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_newsclubandcollege,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder viewHolder, int i) {
        Random random = new Random();
        int j = random.nextInt(4);
        News news = newsList.get(i);
        viewHolder.new_iamge.setImageResource(image[j]);
        viewHolder.user_head.setImageResource(R.drawable.blue);
        viewHolder.new_text.setText(news.getNews());
        viewHolder.user_name.setText(news.getUserName());
        viewHolder.user_time.setText(news.getTime());
        viewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}