package com.lvqingyang.jianshu;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/10/17
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class CanvasView extends View {
    private Paint mPaint;
    private static final String TAG = "CanvasView";
    private Picture mPicture=new Picture();
    private GestureDetector mGestureDetector;
    private Path path;
    private Path dst;


    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextSize(100);

        path=new Path();
//        RectF rectF=new RectF(-400, -400, 400, 400);
        float pos= (float) (200*Math.sin(Math.PI/4));
        path.moveTo(pos+160, pos+160);
        path.lineTo(pos, pos);
        path.addCircle(0, 0, 200, Path.Direction.CW);
        dst=new Path();

//        mGestureDetector=new GestureDetector(context, this);//实现OnGestureListener接口
//        mGestureDetector.setIsLongpressEnabled(false);//解决长按屏幕无法拖动的现象
//        mGestureDetector.setOnDoubleTapListener(this);//实现OnDoubleTapListener
//
//        setLongClickable(true);

//        recording();
//        if (BuildConfig.DEBUG) try {
//            Log.d(TAG, "CanvasView: "+Class.forName("android.graphics.Paint"));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (BuildConfig.DEBUG) Log.d(TAG, "CanvasView: "+Paint.class);
//        if (BuildConfig.DEBUG) Log.d(TAG, "CanvasView: "+mPaint.getClass());
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.img);
//        canvas.drawBitmap(bitmap, new Matrix(), null);
//        canvas.drawBitmap(bitmap, 100, 200, null);
//        Rect src=new Rect(0, 0, bitmap.getWidth()/2, bitmap.getHeight());
//        Rect dest=new Rect(0, 0, bitmap.getWidth()/2, bitmap.getHeight()/2);
//        canvas.drawBitmap(bitmap, src, dest, null);

//        canvas.drawText("asgfloi中文", 200, 400, mPaint);

//        canvas.drawCircle(200, 200, 200, mPaint);
//        String text="ag中文";
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStyle(Paint.Style.STROKE);
//        Rect targetRect=new Rect(0, 0, 400, 400);
//        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
//        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
//        // 下面这行是实现水平居中，drawText对应改为传入targetRect.centerX()
//        mPaint.setTextAlign(Paint.Align.CENTER);
//        canvas.drawText(text, targetRect.centerX(), baseline, mPaint);

//        canvas.translate(getMeasuredWidth()/2, getMeasuredHeight()/2);
//        Path path=new Path();
//        path.moveTo(400, 0);
//        RectF rectF=new RectF(-200, -200, 200, 200);
//        path.arcTo(rectF, 0, 135, false);
//        path.offset(100, 100);
//        canvas.drawPath(path, mPaint);

//        //矩形背景
//        Paint bgRect=new Paint();
//        bgRect.setStyle(Paint.Style.FILL);
//        bgRect.setColor(Color.YELLOW);
//        RectF rectF=new RectF(200, 200, 800, 600);
//        canvas.drawRect(rectF, bgRect);
//
//        Paint textPaint=new Paint();
//        textPaint.setStyle(Paint.Style.FILL);
//        textPaint.setStrokeWidth(8);
//        textPaint.setTextSize(50);
//        textPaint.setTextAlign(Paint.Align.CENTER);
//
//        String text="测试：my text";
//        //计算baseline
//        Paint.FontMetrics fontMetrics=textPaint.getFontMetrics();
//        float distance=(fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
//        float baseline=rectF.centerY()+distance;
//        canvas.drawText(text, rectF.centerX(), baseline, textPaint);

        canvas.translate(getMeasuredWidth()/2, getMeasuredHeight()/2);

        mPaint.setColor(Color.CYAN);
        canvas.drawPath(path, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(dst, mPaint);
//        path.addArc(rectF, 90, 180);
//
//        Path path1=new Path();
//        path1.addCircle(0, -200, 200, Path.Direction.CW);
//
//        Path path2=new Path();
//        path2.addCircle(0, 200, 200, Path.Direction.CW);
//
//        path.op(path1, Path.Op.UNION);
//        path.op(path2, Path.Op.DIFFERENCE);




    }

    public void startAnim(){
        final PathMeasure measure=new PathMeasure(path, false);
        ValueAnimator animator=ValueAnimator.ofFloat(0f, 1f)
                .setDuration(2000);
        final float p1=measure.getLength();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dst.reset();
                float stop=p1*((float)animation.getAnimatedValue());
                measure.getSegment(0, stop, dst, true);
                postInvalidate();
            }
        });
        animator.start();
    }

//    private void recording() {
//        Canvas canvas=mPicture.beginRecording(400, 400);
//        canvas.drawColor(Color.GRAY);
//        canvas.drawPoint(0, 0, mPaint);
//        mPaint.setColor(Color.RED);
//        canvas.drawPoint(250, 250, mPaint);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawPoint(500, 500, mPaint);
//
//        mPicture.endRecording();
//
//    }




//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        boolean consume=mGestureDetector.onTouchEvent(event);
//        return consume;
//    }
//
//    @Override
//    public boolean onSingleTapConfirmed(MotionEvent e) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onSingleTapConfirmed: ");
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTap(MotionEvent e) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onDoubleTap: ");
//        return false;
//    }
//
//    @Override
//    public boolean onDoubleTapEvent(MotionEvent e) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onDoubleTapEvent: ");
//        return false;
//    }
//
//    @Override
//    public boolean onDown(MotionEvent e) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onDown: ");
//        return false;
//    }
//
//    @Override
//    public void onShowPress(MotionEvent e) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onShowPress: ");
//
//    }
//
//    @Override
//    public boolean onSingleTapUp(MotionEvent e) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onSingleTapUp: ");
//        return false;
//    }
//
//    @Override
//    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onScroll: ");
//        return false;
//    }
//
//    @Override
//    public void onLongPress(MotionEvent e) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onLongPress: ");
//    }
//
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        if (BuildConfig.DEBUG) Log.d(TAG, "onFling: ");
//        return false;
//    }

}
