package com.lvqingyang.jianshu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/10/24
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class MyFrameLayout extends FrameLayout {
    private static final String TAG = "ViewGroup";

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (BuildConfig.DEBUG) Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept=super.onInterceptTouchEvent(ev);
        if (BuildConfig.DEBUG) Log.d(TAG, "onInterceptTouchEvent: "+intercept);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
