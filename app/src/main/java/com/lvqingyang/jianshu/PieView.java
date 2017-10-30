package com.lvqingyang.jianshu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

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
public class PieView extends View {

    public static class PieData{
        private String name;
        private float value;
        private float percentage;
        private int color;
        private float angle;

        public PieData(String name, float value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public float getPercentage() {
            return percentage;
        }

        public void setPercentage(float percentage) {
            this.percentage = percentage;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public float getAngle() {
            return angle;
        }

        public void setAngle(float angle) {
            this.angle = angle;
        }
    }

    private Paint mPaint;
    private List<PieData> mPieDataList;
    private float mStartAngle;
    public static int[] mColorIds=new int[]{
            R.color.accent_red,
            R.color.accent_pink,
            R.color.accent_purple,
            R.color.accent_deep_purple,
            R.color.accent_indago,
            R.color.accent_blue,
            R.color.accent_cyan,
            R.color.accent_teal,
            R.color.accent_green,
            R.color.accent_yellow,
            R.color.accent_amber,
            R.color.accent_orange,
            R.color.accent_brown,
            R.color.accent_white,
            R.color.accent_grey,
            R.color.accent_black,
    };
    private static final String TAG = "PieView";

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //init paint
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                        attrs,
                        R.styleable.PieView,
                        0, 0);
        try {
            mStartAngle=a.getFloat(R.styleable.PieView_startAngle, 0f);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPieDataList == null || mPieDataList.size()==0) {
            return;
        }

        int width=getMeasuredWidth(),
                height=getMeasuredHeight();
        float radius=Math.min(width, height)/2f;

        canvas.translate(width/2f, height/2f);
        RectF rectF=new RectF(radius*-1, radius*-1, radius, radius);
        float currentAngle=mStartAngle;
        for (PieData pieData : mPieDataList) {
            mPaint.setColor(pieData.color);
            canvas.drawArc(rectF, currentAngle, pieData.angle, true, mPaint);
            currentAngle+=pieData.angle;
        }
    }

    public void setPieDataList(List<PieData> dataList){
        mPieDataList=dataList;
        initData(dataList);
        invalidate();
    }

    private void initData(List<PieData> dataList) {
        float sumData=0;
        for (int i = 0; i < dataList.size(); i++) {
            sumData+=dataList.get(i).value;
            dataList.get(i).setColor(getResources().getColor(mColorIds[i%mColorIds.length]));
        }

        for (PieData pieData : dataList) {
            float per=pieData.value/sumData;
            pieData.setPercentage(per);
            pieData.setAngle(360*per);
        }
    }

    public void setStartAngle(float angle){
        mStartAngle=angle;
        invalidate();
    }

}
