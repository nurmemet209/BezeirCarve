package com.nurmemet.bezeircurve;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nurmemet on 10/9/2016.
 */

public class BezeirCurveView extends View {

    private final static short width = 1000;
    private final static short height = 800;

    private final static short radius = 20;
    private final static short innerRadius = 10;

    private static final PointF start = new PointF(50, height/2);
    private static final PointF end = new PointF(width-50, height/2);

    private float x = width/2;
    private float y = 50;

    private boolean isDragged = false;
    private RectF mHandleRc;
    private RectF mPointRect;

    private int mActivePointerId = -1;
    private Paint mPaint;
    private Path mPath;


    public BezeirCurveView(Context context) {
        super(context);
    }

    public BezeirCurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezeirCurveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        setWillNotDraw(false);

        mHandleRc = new RectF();
        mHandleRc.set(x - radius, y - radius, x + radius, y + radius);

        mPointRect=new RectF();
        mPointRect.set(x - innerRadius, y - innerRadius, x + innerRadius, y + innerRadius);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);

        mPath = new Path();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(start.x, start.y);
        mPath.quadTo(x, y, end.x, end.y);
        canvas.drawOval(mPointRect,mPaint);
        canvas.drawPath(mPath, mPaint);




    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        final float tX = event.getX();
        final float tY = event.getY();

        int actionPoinerIndex;
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                mActivePointerId = event.getPointerId(0);
                if (mHandleRc.contains(tX, tY)) {
                    isDragged = true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == -1) {
                    return false;
                }
                actionPoinerIndex = event.findPointerIndex(mActivePointerId);
                if (actionPoinerIndex < 0) {
                    return false;
                }
                if (isDragged) {
                    x = tX;
                    y = tY;
                    mHandleRc.set(x - radius, y - radius, x + radius, y + radius);
                    mPointRect.set(x - innerRadius, y - innerRadius, x + innerRadius, y + innerRadius);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //如果活动手指已松开，结束这次触摸事件的捕获
                actionPoinerIndex = event.getActionIndex();
                final int actionPointerId = event.getPointerId(actionPoinerIndex);
                if (actionPointerId == mActivePointerId) {
                    mActivePointerId = -1;
                    return false;
                }
                isDragged=false;
                break;
            case MotionEvent.ACTION_UP:
                isDragged=false;
                break;
        }
        return isDragged;
    }
}
