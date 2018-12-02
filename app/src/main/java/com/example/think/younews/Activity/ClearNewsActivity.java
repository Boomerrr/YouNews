package com.example.think.younews.Activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.think.younews.R;

public class ClearNewsActivity extends Activity implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private static final float PERCENTAGE_TO_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETATILS = 0.3f;
    private static final int ALIPHA_ANIMATIONS_DURATION = 200;

    private Toolbar toolbar;
    private TextView tvTitleName;
    private TextView tvName;
    private TextView tvRegion;
    private TextView tvSex;
    private RelativeLayout mTitleContainer;
    private AppBarLayout mAppBarLayout;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTileContainerVisible = true;
    private EditText editText;
    private Button send;
    private FloatingActionButton floatingActionButton;

    private PopupWindow popupWindow = new PopupWindow();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_news);
        intiViewFunction();
        toolbar.setNavigationIcon(R.drawable.arrow_left);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void intiViewFunction() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitleName = (TextView) findViewById(R.id.tv_title_name);
        tvName = (TextView) findViewById(R.id.tv_name);
        mTitleContainer = (RelativeLayout) findViewById(R.id.title_container);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBar_layout);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(ClearNewsActivity.this,R.layout.addperson);
            }
        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(i) / (float) maxScroll;
        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if(percentage >= PERCENTAGE_TO_TITLE_AT_TOOLBAR){
            if(!mIsTheTitleVisible){
                startAlphaAnimation(tvTitleName, ALIPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = false;
            }
        }else{
            if(!mIsTheTileContainerVisible){
                startAlphaAnimation(mTitleContainer,ALIPHA_ANIMATIONS_DURATION,View.INVISIBLE);
                    mIsTheTileContainerVisible = true;
                }
            }
    }

    public  static void startAlphaAnimation(View v, int duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f,1f)
                :new AlphaAnimation(1f,0f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);

    }

    private void handleAlphaOnTitle(float percentage) {
        if(percentage >= PERCENTAGE_TO_HIDE_TITLE_DETATILS){
            if(mIsTheTileContainerVisible){
                startAlphaAnimation(mTitleContainer,ALIPHA_ANIMATIONS_DURATION,View.INVISIBLE);
                mIsTheTileContainerVisible  = false;
            }
        }else{
            if(!mIsTheTileContainerVisible){
                startAlphaAnimation(mTitleContainer,ALIPHA_ANIMATIONS_DURATION,View.VISIBLE);
                mIsTheTileContainerVisible = true;
            }
        }

    }



    private void   showPopupWindow(Context context, @LayoutRes int resource) {
        //设置要显示的view
        View view = View.inflate(context, resource, null);
        //此处可按需求为各控件设置属性

        popupWindow = new PopupWindow(view);
        //设置弹出窗口大小
        popupWindow.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //必须设置以下两项，否则弹出窗口无法取消
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        editText = (EditText)view. findViewById(R.id.edt_comment);
        send = (Button)view. findViewById(R.id.comment_btn);
        send.setOnClickListener(this);
        //设置动画效果
        //popupWindow.setAnimationStyle(R.style.AnimBottom);
        //设置显示位置,findViewById获取的是包含当前整个页面的view
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void sendComment() {
        String commentString = editText.getText().toString();
        if(!commentString.equals("")){
            editText.setText("");
            Toast.makeText(this,"评论成功",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"评论失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_btn:
                sendComment();

        }
    }
}
