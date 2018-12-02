package com.example.think.younews.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.R;

public class CustomManagerFragment extends BaseFragment {
    @Override
    protected View initView() {
        View view = View.inflate(getActivity(), R.layout.fragment_custommanager,null);
        init(view);
        return view;
    }

    private void init(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        LogInFragment logInFragment = new LogInFragment();
        if(!logInFragment.isAdded()){
            ft.add(R.id.framelayout,logInFragment);
        }else{
            ft.show(logInFragment);
        }
        ft.commit();
    }

}
