package com.example.think.younews.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.think.younews.Activity.WorkActivity;
import com.example.think.younews.Base.BaseFragment;
import com.example.think.younews.IViews.ChangeView;
import com.example.think.younews.Presenter.SignInPresenter;
import com.example.think.younews.R;

public class SignInFragment extends BaseFragment implements ChangeView{
    private SignInPresenter presenter;
    private EditText signInStudentCode;
    private EditText signinCode;
    private Button signIn;
    private CustomFragment customFragment;
    @Override
    protected View initView() {
        View view=View.inflate(mContext, R.layout.fragment_signin,null);
        presenter = new SignInPresenter(this);
        initFunction(view);
        return view;
    }

    private void initFunction(View view) {
        signInStudentCode = (EditText) view.findViewById(R.id.signin_studentCode);
        signinCode = (EditText) view.findViewById(R.id.signin_code);
        signIn = (Button) view.findViewById(R.id.signin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCodeFunction();
            }
        });
    }

    private void checkCodeFunction() {
        String studentCodeString = signInStudentCode.getText().toString();
        String codeString = signinCode.getText().toString();
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
