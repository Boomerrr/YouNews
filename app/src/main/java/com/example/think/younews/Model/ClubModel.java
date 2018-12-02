package com.example.think.younews.Model;

import com.example.think.younews.ICallback.YouCallBack;

public class ClubModel {
    public  void getData(String parmas,  YouCallBack youCallBack){
        youCallBack.onComplete();
        youCallBack.onError();
        youCallBack.onFail("llll");
        youCallBack.onSuccess("ok");
    }
}
