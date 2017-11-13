package com.lvqingyang.jianshu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/11/12
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class SearchView extends View{
    private int mColor;
    private int mAnimTime;
    private int mStrokeWidth;
    private int mDefaultLength=100; //默认长度

    private int mWidth, mHeight;
    private float mValue;

    private Paint mPaint;
    private Path mPathSearch;
    private Path mPahLoading;
    private PathMeasure mMeasure=new PathMeasure();
    private ValueAnimator mValueAnimator;
    private State mState=State.NONE;
    private static final String TAG = "SearchView";
    private boolean mIsToStop=false;

    public static enum State{
        NONE,   //初始化状态
        STARTING,   //开始搜索
        SEARCHING,  //搜索中...
        ENDING,     //结束搜索
    }

    public void startSearch(){
        mState=State.STARTING;
        mValueAnimator.start();
    }

    public void stopSearch(){
        mIsToStop=true;
    }

    public State getState(){
        return mState;
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        parseAttrs(attrs);
        initPaint();
        initAnim();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode== MeasureSpec.AT_MOST && heightMode== MeasureSpec.AT_MOST) {
            setMeasuredDimension(mDefaultLength, mDefaultLength);
        }else if (widthMode== MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultLength, height);
        }else if (heightMode== MeasureSpec.AT_MOST){
            setMeasuredDimension(width, mDefaultLength);
        }else {
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        initPath();
        postInvalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2f, mHeight/2f);

        switch (mState) {
            case NONE:{
                canvas.drawPath(mPathSearch, mPaint);
                break;
            }
            case STARTING:{
                mMeasure.setPath(mPathSearch, false);
                Path path1=new Path();
                mMeasure.getSegment(mValue*mMeasure.getLength(), mMeasure.getLength(),
                        path1, true);
                canvas.drawPath(path1, mPaint);
                break;
            }
            case SEARCHING:{
                mMeasure.setPath(mPahLoading, false);
                Path path2=new Path();
                float stop=mMeasure.getLength()*mValue;
                float start= (float) (stop-(0.5-Math.abs(0.5-mValue))*mMeasure.getLength()*0.4);
                mMeasure.getSegment(start, stop, path2, true);
                canvas.drawPath(path2, mPaint);
                break;
            }
            case ENDING:{
                mMeasure.setPath(mPathSearch, false);
                Path path3=new Path();
                mMeasure.getSegment((1-mValue)*mMeasure.getLength(), mMeasure.getLength(),
                        path3, true);
                canvas.drawPath(path3, mPaint);
                break;
            }
            default:{
                canvas.drawPath(mPathSearch, mPaint);
                break;
            }
        }
    }

    /**
     * 解析属性
     * @param attrs
     */
    private void parseAttrs(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SearchView,
                0, 0);

        try {
            //View颜色
            mColor=a.getColor(R.styleable.SearchView_color, Color.WHITE);
            //线条宽度
            mStrokeWidth=a.getDimensionPixelSize(R.styleable.SearchView_stroke_width, 6);
            //动画时间
            mAnimTime=a.getInteger(R.styleable.SearchView_anim_time, 1500);
        } finally {
            a.recycle();
        }
    }


    private void initPaint() {
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }

    private void initPath() {
        int length=Math.min(mWidth, mHeight)-mStrokeWidth;
        float quarter=length/4f;
        float half=length/2f;
//        float halfStrokeWidth=mStrokeWidth/2f;

        mPathSearch=new Path();
        RectF rectF1=new RectF(quarter*-1, quarter*-1, quarter, quarter);
        mPathSearch.addArc(rectF1, 45, 359.9f);
        float v= (float) Math.sin(Math.PI/4);
        mPathSearch.lineTo(half*v, half*v);

        mPahLoading=new Path();
        RectF rectF2=new RectF(half*-1, half*-1, half, half);
        mPahLoading.addArc(rectF2, 45, -359.9f);

    }

    private void initAnim() {
        mValueAnimator=ValueAnimator.ofFloat(0, 1).setDuration(mAnimTime);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValue= (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (BuildConfig.DEBUG) Log.d(TAG, "onAnimationEnd: ");
                switch (mState) {
                    case NONE:{
                        break;
                    }
                    case STARTING:{
                        mState=State.SEARCHING;
                        mValueAnimator.start();
                        break;
                    }
                    case SEARCHING:{
                        if (mIsToStop) {
                            mState=State.ENDING;
                            mIsToStop=false;
                        }
                        mValueAnimator.start();
                        break;
                    }
                    case ENDING:{
                        mState=State.NONE;
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });
    }
}
