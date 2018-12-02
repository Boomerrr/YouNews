package com.example.think.younews.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.think.younews.Adapter.UserNewsAdapter;
import com.example.think.younews.Bean.UserNews;
import com.example.think.younews.New.UserNewss;
import com.example.think.younews.R;

import java.util.ArrayList;
import java.util.List;

public class ClearUserNewsActivity extends Activity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_user_news);
        List<UserNews> userNewsList = new ArrayList<>();
        userNewsList = UserNewss.getData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        UserNewsAdapter userNewsAdapter = new UserNewsAdapter(userNewsList);
        recyclerView.setAdapter(userNewsAdapter);
    }
}
