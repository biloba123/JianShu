package com.lvqingyang.jianshu;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/9/16
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class TouchView extends android.support.v7.widget.AppCompatTextView {
    private int lastX,lastY;

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x= (int) event.getRawX();
        int y= (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                setTranslationX(getTranslationX()+x-lastX);
                setTranslationY(getTranslationY()+y-lastY);
//                ObjectAnimator oa1=ObjectAnimator.ofFloat(this,"translationX",getTranslationX()+x-lastX);
//                ObjectAnimator oa2=ObjectAnimator.ofFloat(this,"translationY",getTranslationY()+y-lastY);
//                AnimatorSet set=new AnimatorSet();
//                set.play(oa1)
//                        .with(oa2);
//                set.setDuration(1).start();
                break;
            }
            case MotionEvent.ACTION_UP:{
                break;
            }
        }

        lastX=x;
        lastY=y;
        return true;
    }



}
