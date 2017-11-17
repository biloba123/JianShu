package com.lvqingyang.jianshu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/11/15
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class HorizontalScrollViewEx extends ViewGroup {
//    private VelocityTracker mVelocityTracker=VelocityTracker.obtain();
//    private Scroller mScroller;

    public HorizontalScrollViewEx(Context context) {
        this(context, null);
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=MeasureSpec.getSize(widthMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int paddingH=getPaddingLeft()+getPaddingRight();
        int paddingV=getPaddingTop()+getPaddingBottom();

        int childCount=getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        if(childCount==0){
            //无孩子时根据自身padding和MeasureSpec测量
            if (widthMode== MeasureSpec.AT_MOST&&heightMode== MeasureSpec.AT_MOST) {
                setMeasuredDimension(paddingH, paddingV);
            }else if (widthMode== MeasureSpec.AT_MOST){
                setMeasuredDimension(paddingH, height);
            }else if (heightMode== MeasureSpec.AT_MOST){
                setMeasuredDimension(width, paddingV);
            }else {
                setMeasuredDimension(width, height);
            }
        }else {
            int maxHeight=0;
            int widthMeasure=paddingH;
            for (int i = 0; i < childCount; i++) {
                View child=getChildAt(i);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                widthMeasure+=lp.leftMargin+lp.rightMargin+child.getMeasuredWidth();
                maxHeight=Math.max(maxHeight, child.getMeasuredHeight());
            }
            setMeasuredDimension(widthMeasure, maxHeight+=paddingV);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();
        int childLeft=getPaddingLeft();
        for (int i = 0; i < childCount; i++) {
            View child=getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            childLeft+=lp.leftMargin;
            child.layout(childLeft, getPaddingTop()+lp.topMargin,
                    childLeft+child.getMeasuredWidth(), getPaddingTop()+lp.topMargin+child.getMeasuredHeight());
            childLeft+=child.getMeasuredWidth()+lp.rightMargin;
        }
    }
}
