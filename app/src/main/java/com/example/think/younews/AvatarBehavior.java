package com.example.think.younews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import de.hdodenhof.circleimageview.CircleImageView;

public class AvatarBehavior extends CoordinatorLayout.Behavior<CircleImageView> {
    private static final String TAG = AvatarBehavior.class.getSimpleName();

    private static final float ANIN_CHANGE_POINT = 0.2f;

    private Context mContext;

    private int mTotalScrollRange;
    private int mAppBarHeight;
    private int mAppBarWidth;
    private int mOriginalSize;
    private int mFinalSize;
    private float mScaleSize;
    private float mOriginalX;
    private float mFinalX;
    private float mOriginalY;
    private float mFinalY;
    private int mToolBarHeight;
    private float mAppBarStartY;
    private float mPercent;
    private DecelerateInterpolator mMoveYInterpolator;
    private AccelerateInterpolator mMoveXInterpolator;
    private CircleImageView mFianlView;
    private int mFinalViewMarginBottom;

    public AvatarBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
        mContext = context;
        mMoveYInterpolator = new DecelerateInterpolator();
        mMoveXInterpolator = new AccelerateInterpolator();
        if(attrs != null){
            TypedArray a  = mContext.obtainStyledAttributes(attrs,R.styleable.AvatarImageBehavior);
            mFinalSize = (int) a.getDimension(R.styleable.AvatarImageBehavior_finalSize,0);
            mFinalX = a.getDimension(R.styleable.AvatarImageBehavior_finalX,0);
            mToolBarHeight = (int) a.getDimension(R.styleable.AvatarImageBehavior_toolBarHeight,0);
            a.recycle();
        }

    }

    @Override
    public boolean layoutDependsOn( CoordinatorLayout parent,  CircleImageView child,  View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged( CoordinatorLayout parent,  CircleImageView child,  View dependency) {

        if(dependency instanceof  AppBarLayout){
            _initVariables(child, dependency);
            mPercent = (mAppBarStartY - dependency.getY()) * 1.0f / mTotalScrollRange;
            float percentY = mMoveYInterpolator.getInterpolation(mPercent);
            setViewY(child, mOriginalY, mFinalY - mScaleSize, percentY);
            if(mPercent > ANIN_CHANGE_POINT){
                float scalePercent = (mPercent - ANIN_CHANGE_POINT) / (1 - ANIN_CHANGE_POINT);
                float percentX = mMoveXInterpolator.getInterpolation(scalePercent);
                scaleView(child, mOriginalSize, mFinalSize, scalePercent);
                setViewX(child,mOriginalX,mFinalX - mScaleSize,percentX);
            }else{
                scaleView(child,mOriginalSize,mFinalSize,0);
                setViewX(child,mOriginalX,mFinalX - mScaleSize,0);
            }
            if(mFianlView != null){
                if(percentY ==0.1f){
                    mFianlView.setVisibility(View.VISIBLE);
                }else{
                    mFianlView.setVisibility(View.GONE);
                }
            }
        }else if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP && dependency instanceof CollapsingToolbarLayout){
            if(mFianlView == null && mFinalSize != 0 && mFinalViewMarginBottom != 0){
                mFianlView = new CircleImageView(mContext);
                mFianlView.setVisibility(View.GONE);
                ((CollapsingToolbarLayout) dependency).addView(mFianlView);
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mFianlView.getLayoutParams();
                params.width = mFinalSize;
                params.height = mFinalSize;
                params.gravity = Gravity.BOTTOM;
                params.leftMargin = (int) mFinalX;
                params.bottomMargin = mFinalViewMarginBottom;
                mFianlView.setLayoutParams(params);
                mFianlView.setImageDrawable(child.getDrawable());
                mFianlView.setBorderColor(child.getBorderColor());
                int borderWidth = (int) ((mFinalSize * 1.0f / mOriginalSize) * child.getBorderWidth());
                mFianlView.setBorderWidth(borderWidth);
            }
            if(mFianlView != null && mFinalSize != 0 && mFinalViewMarginBottom != 0){
                mFianlView.setImageDrawable(child.getDrawable());
            }
        }

        return true;
    }

    private void setViewX(View view, float mOriginalX, float v, float percentX) {
        float calcX = (mFinalX - mOriginalY) * percentX + mOriginalX;
        view.setX(calcX);
    }

    private static void scaleView(View view, float originalSize, float finalSize, float percent) {
        float calcSize = (finalSize - originalSize) * percent + originalSize;
        float caleScale = calcSize / originalSize;
        view.setScaleX(caleScale);
        view.setScaleY(caleScale);
    }

    private void setViewY(View view, float originalY, float finalY, float percent) {
        float calcY = (finalY - originalY) * percent + originalY;
        view.setY(calcY);
    }

    private void _initVariables(CircleImageView child, View dependency) {
        if(mAppBarHeight == 0){
            mAppBarHeight = dependency.getHeight();
            mAppBarStartY = dependency.getY();
        }
        if(mTotalScrollRange == 0){
            mTotalScrollRange = ((AppBarLayout)dependency).getTotalScrollRange();
        }
        if(mOriginalSize == 0){
            mOriginalSize = child.getWidth();
        }
        if(mFinalSize == 0){
            mFinalSize = mContext.getResources().getDimensionPixelSize(R.dimen.avatar_final_size);
        }
        if(mAppBarWidth == 0){
            mAppBarWidth = dependency.getWidth();
        }
        if(mOriginalX == 0){
            mOriginalX = child.getX();
        }
        if(mFinalX == 0){
            mFinalX = mContext.getResources().getDimensionPixelSize(R.dimen.avatar_final_x);
        }
        if(mOriginalY == 0){
            mOriginalY = child.getY();
        }
        if(mFinalY == 0){
           if(mToolBarHeight == 0){
               mToolBarHeight =  mContext.getResources().getDimensionPixelSize(R.dimen.toolbar_height);;
           }
           int statusBarHeight =  mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_height);
           mFinalY = (mToolBarHeight - mFinalSize) / 2 + mAppBarStartY + statusBarHeight;
        }
        if(mScaleSize == 0){
            mScaleSize = (mOriginalSize - mFinalSize) / 2;
        }
    }
}
