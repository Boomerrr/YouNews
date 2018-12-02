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
import com.example.think.younews.Adapter.ClubNewsAdapter;
import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.Bean.News;
import com.example.think.younews.IViews.IView;
import com.example.think.younews.New.Newss;
import com.example.think.younews.Presenter.ClubPresenter;
import com.example.think.younews.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentClub extends BaseFragment implements IView,ClubNewsAdapter.OnItemClickListener{
    private ClubPresenter presenter;
    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = false;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.fragment_club,null);
        presenter = new ClubPresenter(this);
        setRecycler(view);
        return view;
    }

    private void setRecycler(View view) {
        List<News> news = new ArrayList<>();
       news = Newss.getData();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        ClubNewsAdapter newsAdapter = new ClubNewsAdapter(news);
        newsAdapter.setItemClickListener(this);
        recyclerView.setAdapter(newsAdapter);
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
    private List<News> initDataFunction() {
        List<News> userNewsList = new ArrayList<>();
        News userNews = new News();
        userNews.setTime("10:10");
        userNews.setUserName("杜甫");
        userNews.setNews("会当凌绝顶，一览众山小。");
        userNewsList.add(userNews);

        News userNews1 = new News();
        userNews1.setTime("10:10");
        userNews1.setUserName("李白");
        userNews1.setNews("举头望明月，低头思故乡。");
        userNewsList.add(userNews1);

        News userNews2 = new News();
        userNews2.setTime("10:10");
        userNews2.setUserName("孟浩然");
        userNews2.setNews("春眠不觉晓，处处闻啼鸟。");
        userNewsList.add(userNews2);

        News userNews3 = new News();
        userNews3.setTime("10:10");
        userNews3.setUserName("刘禹锡");
        userNews3.setNews("山不在高，有仙则名。");
        userNewsList.add(userNews3);

        News userNews4 = new News();
        userNews4.setTime("10:10");
        userNews4.setUserName("李贺");
        userNews4.setNews("大漠沙如雪，燕山月似钩。");
        userNewsList.add(userNews4);

        News userNews5 = new News();
        userNews5.setTime("10:10");
        userNews5.setUserName("贺知章");
        userNews5.setNews("少小离家老大回，乡音无改鬓毛衰。");
        userNewsList.add(userNews5);

        News userNews6 = new News();
        userNews6.setTime("10:10");
        userNews6.setUserName("龚自珍");
        userNews6.setNews("落红不是无情物，化作春泥更护花。");
        userNewsList.add(userNews6);

        News userNews7 = new News();
        userNews7.setTime("10:10");
        userNews7.setUserName("白居易");
        userNews7.setNews("令公桃李满天下，何用堂前更种花。");
        userNewsList.add(userNews7);

        News userNews8 = new News();
        userNews8.setTime("10:10");
        userNews8.setUserName("罗隐");
        userNews8.setNews("采得百花成蜜后，为谁辛苦为谁甜?");
        userNewsList.add(userNews8);

        News userNews9 = new News();
        userNews9.setTime("10:10");
        userNews9.setUserName("冰心");
        userNews9.setNews("玉壶存冰心，朱笔写师魂。");
        userNewsList.add(userNews9);

        News userNews10 = new News();
        userNews10.setTime("10:10");
        userNews10.setUserName("屈原");
        userNews10.setNews("长太息以掩涕兮，哀民生之多艰");
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
