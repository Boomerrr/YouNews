package com.example.think.younews.Fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.think.younews.Activity.ClearNewsActivity;
import com.example.think.younews.Activity.WorkActivity;
import com.example.think.younews.Adapter.MainNewAdapter;
import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.Bean.MainNew;
import com.example.think.younews.IViews.IView;
import com.example.think.younews.New.MainNews;
import com.example.think.younews.Presenter.MainPresenter;
import com.example.think.younews.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentMainNews extends BaseFragment implements IView,MainNewAdapter.OnItemClickListener{
    private MainPresenter presenter;
    private ViewPager mViewPager;
    // 图片都存放在这里
    private List<ImageView> imageViewlist;
    private ImageView iv;
    private TextView imgDes;
    // 线程开关，当activity销毁后，线程也应该停止运行
    private boolean isStop;
    private int previousPoint = 0;
    // 存放小点的布局文件
    private LinearLayout layoutPGroup;
    private String[] imageDescription = { "开学注意事项", "欢迎新同学",
            "学校迎来第五次教学评估", "重邮40年校庆", "招聘季到来" };

    private int mTouchSlop;
    private float mFirstY;
    private float mCurrentY;
    private int direction;
    private ObjectAnimator mAnimator;
    private boolean mShow = false;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.fragment_mainnews,null);
        presenter = new MainPresenter(this);
        isStop = false;
        mTouchSlop = ViewConfiguration.get(getActivity()).getScaledTouchSlop();
        init(view);
        setRecycler(view);
        runnable();
        return view;
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

    private void setRecycler(View view) {
        List<MainNew> news = new ArrayList<>();
        news = MainNews.getData();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        MainNewAdapter mainNewAdapter=new MainNewAdapter(news);
        mainNewAdapter.setItemClickListener(this);
        recyclerView.setAdapter(mainNewAdapter);
        recyclerView.setOnTouchListener(myTouchListener);
    }



    private void init(View view) {
        mViewPager = (ViewPager)view .findViewById(R.id.viewpager);
        layoutPGroup = (LinearLayout) view.findViewById(R.id.show_pointer);
        imgDes = (TextView) view.findViewById(R.id.image_description);
        imageViewlist = new ArrayList<ImageView>();

        // 先拿到图片id
        int[] ivIDs = new int[] { R.drawable.main1, R.drawable.main2, R.drawable.main3,
                R.drawable.main4, R.drawable.main5 };
        // 遍历图片id
        for (int id : ivIDs) {
            // 构造新的图片对象，并根据id给图片设置背景
            iv = new ImageView(getActivity());
            iv.setBackgroundResource(id);
            // 所有的图片存放在imageViewlist中
            imageViewlist.add(iv);

            // 构造小点
            View v = new View(getActivity());
            // 设置小点的宽和高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8, 8);
            // 设置小点的左边距
            params.leftMargin = 12;
            v.setLayoutParams(params);
            // 设置小点是否可用，默认都不可用，当不可用时，小点是透明的，否则是白色的
            v.setEnabled(false);
            // 设置小点的背景，这个背景是使用xml文件画的一个小圆点
            v.setBackgroundResource(R.drawable.pointer_selector);
            // 把小点添加到它的布局文件中
            layoutPGroup.addView(v);
        }
        // 计算应用打开时显示的第一项 Integer.MAX_VALUE /2 - 3=0
        int index = Integer.MAX_VALUE / 2 - 3;
        // 给mViewPager设置数据
        mViewPager.setAdapter(new MyPagerAdapter1());
        // 给mViewPager设置页面滑动事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener1());

        // 设置应用打开时显示的第一项，index的值为0
        // 使用这种方式得到的0，和直接写0有什么区别呢？
        // 直接写0，应用打开后不能直接向右滑动，因为viewpager中存image位置不能为负值，只能先向左滑动
        // 这种方式得到的0，可以实现应用一打开，就可以向右滑动
        mViewPager.setCurrentItem(index);

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

    private void runnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //如果activity未销毁则一直执行
                while (!isStop) {
                    //先休息5秒钟
                    SystemClock.sleep(5000);
                    //以下代码发送到主线程中执行
                    if(getActivity()!=null){
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

    private class MyOnPageChangeListener1 implements ViewPager.OnPageChangeListener {

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


    private class MyPagerAdapter1 extends PagerAdapter {

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

    @Override
    public void onDestroyView() {
        isStop = true;
        super.onDestroyView();
    }


    private List<MainNew> initDataFunction() {
        List<MainNew> userNewsList = new ArrayList<>();
        MainNew userNews = new MainNew();
        userNews.setText("会当凌绝顶，一览众山小。");
        userNewsList.add(userNews);

        MainNew userNews1 = new MainNew();
        userNews1.setText("举头望明月，低头思故乡。");
        userNewsList.add(userNews1);

        MainNew userNews2 = new MainNew();
        userNews2.setText("春眠不觉晓，处处闻啼鸟。");
        userNewsList.add(userNews2);

        MainNew userNews3 = new MainNew();
        userNews3.setText("山不在高，有仙则名。");
        userNewsList.add(userNews3);

        MainNew userNews4 = new MainNew();
        userNews4.setText("大漠沙如雪，燕山月似钩。");
        userNewsList.add(userNews4);

        MainNew userNews5 = new MainNew();
        userNews5.setText("少小离家老大回，乡音无改鬓毛衰。");
        userNewsList.add(userNews5);

        MainNew userNews6 = new MainNew();
        userNews6.setText("落红不是无情物，化作春泥更护花。");
        userNewsList.add(userNews6);

        MainNew userNews7 = new MainNew();
        userNews7.setText("令公桃李满天下，何用堂前更种花。");
        userNewsList.add(userNews7);

        MainNew userNews8 = new MainNew();
        userNews8.setText("采得百花成蜜后，为谁辛苦为谁甜?");
        userNewsList.add(userNews8);

        MainNew userNews9 = new MainNew();
        userNews9.setText("玉壶存冰心，朱笔写师魂。");
        userNewsList.add(userNews9);

        MainNew userNews10 = new MainNew();
        userNews10.setText("长太息以掩涕兮，哀民生之多艰");
        userNewsList.add(userNews10);

        return userNewsList;

    }
}
