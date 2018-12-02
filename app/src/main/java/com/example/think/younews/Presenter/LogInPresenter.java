package com.example.think.younews.Presenter;

import com.example.think.younews.IViews.ChangeView;
import com.example.think.younews.ICallback.YouCallBack;
import com.example.think.younews.Model.LogInModel;
import com.example.think.younews.Util.HttpUtil;

public class LogInPresenter extends BasePresenter {
    private ChangeView iView;
    private LogInModel customModel;
    public LogInPresenter(ChangeView view){
        this.iView=view;
        customModel = new LogInModel();
    }

    public void attachView(ChangeView  view) {
        this.iView= view;
    }

    public void detachView() {
        this.iView= null;
    }

    public boolean isViewAttached(){
        return iView!= null;
    }

    public void checkCode(String name, String password) {

        customModel.getData( name,  password, new YouCallBack() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(String response) {

                if (!response.equals("")) {
                    iView.changeFragment();
                }else{
                    iView.showError();
                }
            }

            @Override
            public void onFail(String msg) {
                iView.showError();
            }

        });
    }
}
