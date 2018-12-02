package com.example.think.younews.Fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.think.younews.Activity.ClearNewsActivity;
import com.example.think.younews.Activity.ClearUserNewsActivity;
import com.example.think.younews.Activity.IssueActivity;
import com.example.think.younews.Activity.WorkActivity;
import com.example.think.younews.Adapter.UserNewsAdapter;
import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.Bean.UserNews;
import com.example.think.younews.IViews.IView;
import com.example.think.younews.New.UserNewss;
import com.example.think.younews.Presenter.CommunityPresenter;
import com.example.think.younews.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityFragment extends BaseFragment implements IView,UserNewsAdapter.OnItemClickListener{
    private CommunityPresenter presenter;
    private ViewPager mViewPager;
    // 图片都存放在这里
    private List<ImageView> imageViewlist;
    private ImageView iv;
    private TextView imgDes;
    // 线程开关，当activity销毁后，线程也应该停止运行
    private boolean isStop = false;
    private int previousPoint = 0;
    // 存放小点的布局文件
    private LinearLayout layoutPGroup;
    private String[] imageDescription = { "","", "",
            "", "" };
    private FloatingActionButton floatingActionButton;
    private CircleImageView head_image;

    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = false;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.fragment_community,null);
        presenter = new CommunityPresenter(this);
        recyclerView(view);
        //runnable();
        return view;
    }

    private void recyclerView(View view) {
        List<UserNews> userNewsList = new ArrayList<>();
        userNewsList = UserNewss.getData();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_community);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        UserNewsAdapter userNewsAdapter = new UserNewsAdapter(userNewsList);
        userNewsAdapter.setItemClickListener(this);
        recyclerView.setAdapter(userNewsAdapter);
        recyclerView.setOnTouchListener(myTouchListener);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),IssueActivity.class));
            }
        });
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


    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail() {

    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(getActivity(), ClearUserNewsActivity.class));
    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        // 开始
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        // 正在进行时
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        // 结束
        @Override
        public void onPageSelected(int position) {
            // 当页面滑动结束时，先对页面位置取模
            position = position % imageViewlist.size();
            // 设置textview的文本内容
            imgDes.setText(imageDescription[position]);
            // 将上一个点的可用性设置为false
            layoutPGroup.getChildAt(previousPoint).setEnabled(false);
            // 把当前点的可用性设置为true
            layoutPGroup.getChildAt(position).setEnabled(true);
            // 把当前位置值赋值给previousPoint
            previousPoint = position;
        }
    }


    private class MyPagerAdapter extends PagerAdapter {

        /**
         * 返回图片总数，Integer.MAX_VALUE的值为 2147483647这个数有21亿，也就是说我们的viewpager
         * 理论上在每次使用应用的时候可以滑动21亿次,当然，实际上是没人要去这么做的，我们这样做是为了实现实现viewpager循环滑动的效果
         * 即当滑动到viewpager的最后一页时，继续滑动就可以回到第一页
         *
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 当某一页滑出去的时候，将其销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewlist.get(position
                    % imageViewlist.size()));
        }

        // 向容器中添加图片，由于我们要实现循环滑动的效果，所以要对position取模
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container
                    .addView(imageViewlist.get(position % imageViewlist.size()));
            return imageViewlist.get(position % imageViewlist.size());
        }
    }

    public void onDestroy() {
        isStop = true;
        super.onDestroy();
    }

    private void runnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //如果activity未销毁则一直执行
                while (!isStop) {
                    //先休息5秒钟
                    SystemClock.sleep(5000);
                    //以下代码发送到主线程中执行
                    if(getActivity() != null){
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager
                                    .getCurrentItem() + 1);
                        }
                    });
                   }else{
                        break;
                    }
                }
            }
        }).start();

    }



    private List<UserNews> initDataFunction() {
        List<UserNews> userNewsList = new ArrayList<>();
        UserNews userNews = new UserNews();
        userNews.setTime("10:10");
        userNews.setUserName("杜甫");
        userNews.setText("会当凌绝顶，一览众山小。");
        userNewsList.add(userNews);

        UserNews userNews1 = new UserNews();
        userNews1.setTime("10:10");
        userNews1.setUserName("李白");
        userNews1.setText("举头望明月，低头思故乡。");
        userNewsList.add(userNews1);

        UserNews userNews2 = new UserNews();
        userNews2.setTime("10:10");
        userNews2.setUserName("孟浩然");
        userNews2.setText("春眠不觉晓，处处闻啼鸟。");
        userNewsList.add(userNews2);

        UserNews userNews3 = new UserNews();
        userNews3.setTime("10:10");
        userNews3.setUserName("刘禹锡");
        userNews3.setText("山不在高，有仙则名。");
        userNewsList.add(userNews3);

        UserNews userNews4 = new UserNews();
        userNews4.setTime("10:10");
        userNews4.setUserName("李贺");
        userNews4.setText("大漠沙如雪，燕山月似钩。");
        userNewsList.add(userNews4);

        UserNews userNews5 = new UserNews();
        userNews5.setTime("10:10");
        userNews5.setUserName("贺知章");
        userNews5.setText("少小离家老大回，乡音无改鬓毛衰。");
        userNewsList.add(userNews5);

        UserNews userNews6 = new UserNews();
        userNews6.setTime("10:10");
        userNews6.setUserName("龚自珍");
        userNews6.setText("落红不是无情物，化作春泥更护花。");
        userNewsList.add(userNews6);

        UserNews userNews7 = new UserNews();
        userNews7.setTime("10:10");
        userNews7.setUserName("白居易");
        userNews7.setText("令公桃李满天下，何用堂前更种花。");
        userNewsList.add(userNews7);

        UserNews userNews8 = new UserNews();
        userNews8.setTime("10:10");
        userNews8.setUserName("罗隐");
        userNews8.setText("采得百花成蜜后，为谁辛苦为谁甜?");
        userNewsList.add(userNews8);

        UserNews userNews9 = new UserNews();
        userNews9.setTime("10:10");
        userNews9.setUserName("冰心");
        userNews9.setText("玉壶存冰心，朱笔写师魂。");
        userNewsList.add(userNews9);

        UserNews userNews10 = new UserNews();
        userNews10.setTime("10:10");
        userNews10.setUserName("屈原");
        userNews10.setText("长太息以掩涕兮，哀民生之多艰");
        userNewsList.add(userNews10);

        return userNewsList;

    }

}
