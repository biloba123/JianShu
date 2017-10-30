package com.lvqingyang.jianshu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/10/28
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class CubicBezier extends View {
    private Paint mPointPaint;
    private Paint mLinePaint;
    private PointF mStart, mEnd, mContral1, mContral2;
    private boolean mMode=true;


    public CubicBezier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPointPaint=new Paint();
        mPointPaint.setStyle(Paint.Style.STROKE);
        mPointPaint.setColor(Color.BLACK);
        mPointPaint.setStrokeWidth(20);
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);

        mLinePaint=new Paint();
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(4);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float centerX=w/2f,
                centerY=h/2f;

        mStart=new PointF(centerX-200, centerY);
        mEnd=new PointF(centerX+200, centerY);
        mContral1=new PointF(centerX-200, centerY-200);
        mContral2=new PointF(centerX+200, centerY-200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPoint(mStart.x, mStart.y, mPointPaint);
        canvas.drawPoint(mEnd.x, mEnd.y, mPointPaint);
        canvas.drawPoint(mContral1.x, mContral1.y, mPointPaint);
        canvas.drawPoint(mContral2.x, mContral2.y, mPointPaint);


        mLinePaint.setColor(Color.BLACK);
        canvas.drawLine(mStart.x, mStart.y, mContral1.x, mContral1.y, mLinePaint);
        canvas.drawLine(mContral1.x, mContral1.y, mContral2.x, mContral2.y, mLinePaint);
        canvas.drawLine(mEnd.x, mEnd.y, mContral2.x, mContral2.y, mLinePaint);

        Path path=new Path();
        path.setLastPoint(mStart.x, mStart.y);
        path.cubicTo(mContral1.x, mContral1.y, mContral2.x, mContral2.y, mEnd.x, mEnd.y);
        mLinePaint.setColor(Color.RED);
        canvas.drawPath(path, mLinePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mMode) {
            mContral1=new PointF(event.getX(), event.getY());
        }else {
            mContral2=new PointF(event.getX(), event.getY());
        }
        invalidate();
        return true;
    }

    public void setMode(boolean mode) {
        mMode = mode;
    }
}
