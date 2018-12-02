package com.example.think.younews.ICallback;

public interface YouCallBack {
    void onComplete();
    void onError();
    void onSuccess(String response);
    void onFail(String msg);
}
