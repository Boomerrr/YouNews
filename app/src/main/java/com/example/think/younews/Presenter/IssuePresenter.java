package com.example.think.younews.Presenter;

import com.example.think.younews.ICallback.YouCallBack;
import com.example.think.younews.IViews.ChangeView;
import com.example.think.younews.IViews.IView;
import com.example.think.younews.Model.IssueModel;
import com.example.think.younews.Model.LogInModel;

public class IssuePresenter extends BasePresenter{
    private IView iView;
    private IssueModel model;
    public IssuePresenter(IView view){
        this.iView = view;
        model = new IssueModel();
    }

    public void attachView(IView  view) {
        this.iView = view;
    }

    public void detachView() {
        this.iView = null;
    }

    public boolean isViewAttached(){
        return iView !=  null;
    }

    public void send(String string,String userNmae){
        model.getData(string, userNmae, new YouCallBack() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(String response) {
                iView.showSuccess();
            }

            @Override
            public void onFail(String msg) {
                iView.showFail();
            }
        });
    }
}
