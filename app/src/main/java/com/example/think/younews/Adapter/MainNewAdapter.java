package com.example.think.younews.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.think.younews.Bean.Comment;
import com.example.think.younews.Bean.MainNew;
import com.example.think.younews.R;

import java.util.List;
import java.util.Random;

public class MainNewAdapter extends RecyclerView.Adapter<MainNewAdapter.ViewHolder>implements View.OnClickListener{
    private List<MainNew> mainNewList;
    private OnItemClickListener mItemClickListener;
    private int[] image = {R.drawable.main1,R.drawable.main2,R.drawable.main3,R.drawable.main4,R.drawable.main5};
    static class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout image;
        TextView text;

        public ViewHolder(View view){
            super(view);
            image = (RelativeLayout) view.findViewById(R.id.image);
            text = (TextView) view.findViewById(R.id.text);

        }
    }
    public MainNewAdapter(List<MainNew> mainNewList){
        this.mainNewList = mainNewList;
    }
    @NonNull
    @Override
    public MainNewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mainnews,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainNewAdapter.ViewHolder viewHolder, int i) {
        Random random = new Random();
        int j = random.nextInt(5);
        MainNew mainNew = mainNewList.get(i);
        viewHolder.text.setText(mainNew.getText());
        viewHolder.image.setBackgroundResource(image[j]);
        viewHolder.itemView.setTag(i);

    }

    @Override
    public int getItemCount() {
        return mainNewList.size();
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
