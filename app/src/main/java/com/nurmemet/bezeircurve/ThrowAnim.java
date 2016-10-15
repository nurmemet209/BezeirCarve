package com.nurmemet.bezeircurve;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by nurmemet on 10/16/2016.
 */

public class ThrowAnim {

    private static final boolean isDebug = false;
    private ImageView mAnimItem;
    private int mDuration = 1000;
    private PointF mStart;
    private PointF mEnd;

    private ViewGroup mContentView;
    private Context mContext;
    private int mContentTop;
    private Path mPath;
    private PathMeasure mPathMeasuer;
    private Path mDesPath;
    private float[] mDesPoint=new float[2];


    public ThrowAnim(Context mContext, ViewGroup contentView, PointF mEnd, PointF mStart) {
        this.mContext = mContext;
        this.mContentView = contentView;
        this.mEnd = mEnd;
        this.mStart = mStart;
        mPath=new Path();
        mPathMeasuer=new PathMeasure();
        mDesPath=new Path();
    }

    public void move() {
        mPath.reset();
        mPath.moveTo(mStart.x,mStart.y);
        mPath.quadTo(mEnd.x,mStart.y,mEnd.x,mEnd.y);
        mPathMeasuer.setPath(mPath,false);
        int[] contentL = new int[2];
        mContentView.getLocationInWindow(contentL);
        mContentTop = contentL[1];
        mAnimItem = new ImageView(mContext);

        mAnimItem.setImageResource(R.mipmap.ic_launcher);
        mContentView.addView(mAnimItem, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        final ValueAnimator anim = ValueAnimator.ofFloat(0,1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float) animation.getAnimatedValue();
                final float length=mPathMeasuer.getLength();
                mPathMeasuer.getPosTan(length*value,mDesPoint,null);
                //mPathMeasuer.getPosTan()
            }
        });
        anim.setDuration(mDuration);
        anim.setRepeatCount(-1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                mAnimItem.setTranslationX(mDesPoint[0]);
                mAnimItem.setTranslationY(mDesPoint[1]-mContentTop);
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mContentView.removeView(mAnimItem);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }
}
