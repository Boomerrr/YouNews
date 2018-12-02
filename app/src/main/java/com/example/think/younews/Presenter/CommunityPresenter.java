package com.example.think.younews.Presenter;

import com.example.think.younews.IViews.IView;

public class CommunityPresenter extends BasePresenter {
    private IView iView;

    public CommunityPresenter(IView view){
        this.iView=view;
    }

    public void attachView(IView  view) {
        this.iView= view;
    }

    public void detachView() {
        this.iView= null;
    }

    public boolean isViewAttached(){
        return iView!= null;
    }
}
