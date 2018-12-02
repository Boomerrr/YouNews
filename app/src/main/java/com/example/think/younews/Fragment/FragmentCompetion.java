package com.example.think.younews.Fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioGroup;

import com.example.think.younews.Activity.ClearNewsActivity;
import com.example.think.younews.Activity.WorkActivity;
import com.example.think.younews.Adapter.CompetionNewsAdapter;
import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.Bean.CompetionNews;
import com.example.think.younews.IViews.IView;
import com.example.think.younews.New.CompetionNewss;
import com.example.think.younews.Presenter.CompetionPresenter;
import com.example.think.younews.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentCompetion extends BaseFragment implements IView,CompetionNewsAdapter.OnItemClickListener{
    private CompetionPresenter presenter;
    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = false;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.fragment_competionnews,null);
        presenter = new CompetionPresenter(this);
        setRecycler(view);
        return view;
    }

    private void setRecycler(View view) {
        List<CompetionNews> competionNews = new ArrayList<>();
        competionNews = CompetionNewss.getData();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        CompetionNewsAdapter competionNewsAdapter = new CompetionNewsAdapter(competionNews);
        competionNewsAdapter.setItemClickListener(this);
        recyclerView.setAdapter(competionNewsAdapter);
        recyclerView.setOnTouchListener(myTouchListener);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }



    View.OnTouchListener myTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY = event.getY();
                    if (mCurrentY - mFirstY > mTouchSlop) {
                        direction = 0;// down
                    } else if (mFirstY - mCurrentY > mTouchSlop) {
                        direction = 1;// up
                    }
                    if (direction == 1) {
                        if (mShow) {
                            toolbarAnim(1);//hide
                            mShow = !mShow;
                        }
                    } else if (direction == 0) {
                        if (!mShow) {
                            toolbarAnim(0);//show
                            mShow = !mShow;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };

    private void toolbarAnim(int flag) {
        WorkActivity workActivity = (WorkActivity)getActivity();
        RadioGroup radioGroup =  workActivity.findViewById(R.id.rd_main);
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        }
        if (flag == 0) {
            mAnimator = ObjectAnimator.ofFloat(radioGroup,
                    "translationY", radioGroup.getTranslationY(), 0);
        } else {
            mAnimator = ObjectAnimator.ofFloat(radioGroup,
                    "translationY", radioGroup.getTranslationY(),
                    -radioGroup.getHeight());
        }
        mAnimator.start();
    }
    private List<CompetionNews> initDataFunction() {
        List<CompetionNews> userNewsList = new ArrayList<>();
        CompetionNews userNews = new CompetionNews();
        userNews.setTime("10:10");
        userNews.setTitle("杜甫");
        userNews.setText("会当凌绝顶，一览众山小。");
        userNewsList.add(userNews);

        CompetionNews userNews1 = new CompetionNews();
        userNews1.setTime("10:10");
        userNews1.setTitle("李白");
        userNews1.setText("举头望明月，低头思故乡。");
        userNewsList.add(userNews1);

        CompetionNews userNews2 = new CompetionNews();
        userNews2.setTime("10:10");
        userNews2.setTitle("孟浩然");
        userNews2.setText("春眠不觉晓，处处闻啼鸟。");
        userNewsList.add(userNews2);

        CompetionNews userNews3 = new CompetionNews();
        userNews3.setTime("10:10");
        userNews3.setTitle("刘禹锡");
        userNews3.setText("山不在高，有仙则名。");
        userNewsList.add(userNews3);

        CompetionNews userNews4 = new CompetionNews();
        userNews4.setTime("10:10");
        userNews4.setTitle("李贺");
        userNews4.setText("大漠沙如雪，燕山月似钩。");
        userNewsList.add(userNews4);

        CompetionNews userNews5 = new CompetionNews();
        userNews5.setTime("10:10");
        userNews5.setTitle("贺知章");
        userNews5.setText("少小离家老大回，乡音无改鬓毛衰。");
        userNewsList.add(userNews5);

        CompetionNews userNews6 = new CompetionNews();
        userNews6.setTime("10:10");
        userNews6.setTitle("龚自珍");
        userNews6.setText("落红不是无情物，化作春泥更护花。");
        userNewsList.add(userNews6);

        CompetionNews userNews7 = new CompetionNews();
        userNews7.setTime("10:10");
        userNews7.setTitle("白居易");
        userNews7.setText("令公桃李满天下，何用堂前更种花。");
        userNewsList.add(userNews7);

        CompetionNews userNews8 = new CompetionNews();
        userNews8.setTime("10:10");
        userNews8.setTitle("罗隐");
        userNews8.setText("采得百花成蜜后，为谁辛苦为谁甜?");
        userNewsList.add(userNews8);

        CompetionNews userNews9 = new CompetionNews();
        userNews9.setTime("10:10");
        userNews9.setTitle("冰心");
        userNews9.setText("玉壶存冰心，朱笔写师魂。");
        userNewsList.add(userNews9);

        CompetionNews userNews10 = new CompetionNews();
        userNews10.setTime("10:10");
        userNews10.setTitle("屈原");
        userNews10.setText("长太息以掩涕兮，哀民生之多艰");
        userNewsList.add(userNews10);

        return userNewsList;

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail() {

    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getActivity(), ClearNewsActivity.class));
    }
}
