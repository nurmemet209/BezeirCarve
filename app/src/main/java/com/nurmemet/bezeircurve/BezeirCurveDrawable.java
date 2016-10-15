package com.nurmemet.bezeircurve;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * Created by nurmemet on 10/15/2016.
 */

public class BezeirCurveDrawable extends Drawable {


    private Paint mPaint;
    private int mColor = Color.RED;
    private PointF p1 = new PointF(), p2 = new PointF(), p3 = new PointF(), p4 = new PointF();
    private Path mPath;
    private RectF mBounds;
    private float mWidth = 500;
    private float mHeight = 1000;

    public BezeirCurveDrawable() {
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new RectF();
        mPaint.setColor(mColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPath = new Path();

    }

    @Override
    public void draw(Canvas canvas) {
        mPath.reset();
        mPaint.setStrokeWidth(4);
        mPaint.setColor(mColor);
        mPath.moveTo(4, 4);
        mPath.cubicTo(100, 500, 300, 100, 600, 500);
        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(4,4,mPaint);
        canvas.drawPoint(100,500,mPaint);
        canvas.drawPoint(300,100,mPaint);
        canvas.drawPoint(600,500,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mBounds.set(left, top, right, bottom);

    }

    @Override
    public int getIntrinsicWidth() {
        return (int) mWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) mHeight;
    }
}
