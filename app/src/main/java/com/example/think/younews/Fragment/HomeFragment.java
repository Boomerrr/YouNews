package com.example.think.younews.Fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;

import com.example.think.younews.Activity.WorkActivity;
import com.example.think.younews.Adapter.FragmentAdapter;
import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.IViews.IView;
import com.example.think.younews.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends BaseFragment implements IView {
    private ViewPager viewPager;
    private TabLayout tabs;
    private CircleImageView head_image;
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        head_image = (CircleImageView) view.findViewById(R.id.head_iamge);
        initViewPager(view);
        return view;
    }

    private void initViewPager(View view) {
        tabs = (TabLayout) view.findViewById(R.id.tabs);
        List<String> category = new ArrayList<>();
        category.add("首页");
        category.add("学院/院");
        category.add("组织");
        category.add("赛事");
        for (int i = 0; i < category.size(); i++) {
            tabs.addTab(tabs.newTab().setText(category.get(i)));
        }
        List<BaseFragment> listFragment = new ArrayList<>();
        listFragment.add(new FragmentMainNews());
        listFragment.add(new FragmenCollege());
        listFragment.add(new FragmentClub());
        listFragment.add(new FragmentCompetion());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getFragmentManager(), listFragment, category);
        viewPager.setAdapter(fragmentAdapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabsFromPagerAdapter(fragmentAdapter);
        head_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkActivity workActivity = (WorkActivity)getActivity();
                DrawerLayout drawerLayout =  workActivity.findViewById(R.id.drawablelayout);
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFail() {

    }
}