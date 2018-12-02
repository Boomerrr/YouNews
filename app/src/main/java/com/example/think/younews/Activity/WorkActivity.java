package com.example.think.younews.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.Fragment.CommunityFragment;
import com.example.think.younews.Fragment.CustomFragment;
import com.example.think.younews.Fragment.CustomManagerFragment;
import com.example.think.younews.Fragment.HomeFragment;
import com.example.think.younews.Fragment.LogInFragment;
import com.example.think.younews.Fragment.SignInFragment;
import com.example.think.younews.R;

import java.util.ArrayList;
import java.util.List;

public class WorkActivity extends FragmentActivity {
    private RadioGroup radioGroup;
    private List<BaseFragment> mBaseFragment;
    public int position;
    private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Log.e("Boomerr---test","WorkActivity");
        radioGroup = (RadioGroup) findViewById(R.id.rd_main);
        initFragment();
        setListener();
    }
    private void setListener(){
        radioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        radioGroup.check(R.id.rb_home);
    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch(i){
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_community:
                    position = 1;
                    break;
                case R.id.rb_custom:
                    position = 2;
                    break;
                default:
                    position = 0;
                    break;
            }
            BaseFragment to = getFragment();
            switchFragment(mContent,to);
        }

    }

    public  void switchFragment(Fragment from,Fragment to) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(from!=to){
            mContent=to;
            if(!to.isAdded()){
                if(from!=null){
                    ft.hide(from);
                }
                if(to!=null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else{
                if(from!=null){
                    ft.hide(from);
                }
                if(to!=null){
                    ft.show(to).commit();
                }
            }
        }
    }

    public  BaseFragment getFragment() {
        BaseFragment fragment= mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment=new ArrayList<>();
        mBaseFragment.add(new HomeFragment());
        mBaseFragment.add(new CommunityFragment());
        mBaseFragment.add(new CustomManagerFragment());
        mBaseFragment.add(new SignInFragment());
        mBaseFragment.add(new CustomFragment());

    }

}