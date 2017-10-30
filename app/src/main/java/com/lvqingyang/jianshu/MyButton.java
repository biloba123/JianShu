package com.lvqingyang.jianshu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/9/17
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class MyButton extends TextView {
    private Scroller mScroller;

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }

    public void smoothScrollTo(int destX,int destY,int duration){
        mScroller.startScroll(getScrollX(),getScrollY(),destX-getScrollX(),destY-getScrollY(),duration);
    }

    public void smoothScrollBy(int x,int y,int duration){
        mScroller.startScroll(getScrollX(),getScrollY(),x+getScrollX(),y+getScrollY(),duration);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
