package com.example.think.younews.Model;

import com.example.think.younews.ICallback.YouCallBack;

public class IssueModel {
    public void getData(String studentCode,String code, YouCallBack youCallBack){
        youCallBack.onSuccess("ok");
    }
}
