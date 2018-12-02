package com.example.think.younews.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.think.younews.Bean.Comment;
import com.example.think.younews.Bean.CompetionNews;
import com.example.think.younews.R;

import java.util.List;
import java.util.Random;

public class CompetionNewsAdapter extends RecyclerView.Adapter<CompetionNewsAdapter.ViewHolder> implements View.OnClickListener{
    private List<CompetionNews> competionNewsList;
    private OnItemClickListener mItemClickListener;
    private int[] image = {R.drawable.game1,R.drawable.game2,R.drawable.game3,R.drawable.game4,R.drawable.game5,R.drawable.game6,R.drawable.game7};
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView new_iamge;
        TextView title;
        TextView time;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            new_iamge = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            time = (TextView) view.findViewById(R.id.time);
            text = (TextView) view.findViewById(R.id.text);
        }
    }

    public CompetionNewsAdapter(List<CompetionNews> competionNewsList) {
        this.competionNewsList = competionNewsList;
    }

    @NonNull
    @Override
    public CompetionNewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_newscompetion, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompetionNewsAdapter.ViewHolder viewHolder, int i) {
        Random random =new Random();
        int j = random.nextInt(7);
        CompetionNews competionNews = competionNewsList.get(i);
        viewHolder.time.setText(competionNews.getTime());
        viewHolder.title.setText(competionNews.getTitle());
        viewHolder.text.setText(competionNews.getText());
        viewHolder.new_iamge.setImageResource(image[j]);
        viewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return competionNewsList.size();
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
