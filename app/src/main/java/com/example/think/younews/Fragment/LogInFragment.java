package com.example.think.younews.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.think.younews.Activity.WorkActivity;
import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.IViews.ChangeView;
import com.example.think.younews.Presenter.LogInPresenter;
import com.example.think.younews.R;

public class LogInFragment extends BaseFragment implements ChangeView,View.OnClickListener{
    private LogInPresenter presenter;
    private SignInFragment signInFragment;
    private CustomFragment customFragment;
    private EditText studentCode;
    private EditText code;
    private TextView signin;
    private TextView forget;
    private Button login;
    private FragmentTransaction ft;

    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.fragment_login,null);
        presenter = new LogInPresenter(this);
        initFunction(view);
        return view;
    }

    private void initFunction(View view) {
        studentCode = (EditText) view.findViewById(R.id.login_studentCode);
        code = (EditText) view.findViewById(R.id.login_code);
        signin = (TextView) view.findViewById(R.id.signin);
        forget = (TextView) view.findViewById(R.id.forget);
        login = (Button) view.findViewById(R.id.login);
        signin.setOnClickListener(this);
        forget.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    public  void changeSignFragment() {
       FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
       SignInFragment signInFragment = new SignInFragment();
       if(!signInFragment.isAdded()){
           ft.add(R.id.framelayout,signInFragment);
           ft.hide(this);
       }else{
           ft.show(signInFragment);
            ft.hide(this);
       }
       ft.commit();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signin:
                changeSignFragment();
                break;
            case R.id.forget:
                Toast.makeText(getActivity(),"请联系管理员",Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                LogInFunction();
        }
    }

    private void LogInFunction() {
        String studentCodeString = studentCode.getText().toString();
        String codeString = code.getText().toString();
        presenter.checkCode(studentCodeString,codeString);
    }

    @Override
    public void changeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        CustomFragment customFragment = new CustomFragment();
        if(!customFragment.isAdded()){
            ft.add(R.id.framelayout,customFragment);
            ft.hide(this);
        }else{
            ft.show(customFragment);
            ft.hide(this);
        }
        ft.commit();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"输入错误",Toast.LENGTH_SHORT).show();
    }
}
