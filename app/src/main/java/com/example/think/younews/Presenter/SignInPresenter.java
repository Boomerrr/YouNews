package com.example.think.younews.Presenter;

import com.example.think.younews.IViews.ChangeView;
import com.example.think.younews.ICallback.YouCallBack;
import com.example.think.younews.Model.SignInModel;

public class SignInPresenter extends BasePresenter {
    private ChangeView iView;
    private SignInModel signInModel;

    public SignInPresenter(ChangeView view){
        this.iView=view;
        signInModel = new SignInModel();
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

    public void checkCode(String studentCode,String code){
        signInModel.getData(studentCode, code, new YouCallBack() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(String response) {
                if (response.equals("ok")){
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
