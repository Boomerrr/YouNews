package com.example.think.younews.Model;

import com.example.think.younews.ICallback.YouCallBack;

public class SignInModel {
    public void getData(String studentCode,String code, YouCallBack youCallBack){
        youCallBack.onSuccess("ok");
        youCallBack.onFail("fail");
    }
}
