package com.example.think.younews.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.think.younews.R;
import com.example.think.younews.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {
    public static final String Denglu = "118.190.201.195:8080/youzixun/dengluservlet";
    public static final String Zhuce = "118.190.201.195:8080/youzixun/ZhuceServlet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        HttpUtil.sendOkhttpRequestPost(Zhuce, "2017210188", "123456", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Toast.makeText(TestActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
