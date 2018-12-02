package com.example.think.younews.Util;

import android.app.VoiceInteractor;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequeest(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkhttpRequestPost(String address, String name, String password,  Callback callback){

        try {
            OkHttpClient client=new OkHttpClient();
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            FormBody formBody = formBodyBuilder.add("name",name).add("password",password).build();
            Request request=new Request.Builder().url(
                    address
            ).post(formBody).build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
