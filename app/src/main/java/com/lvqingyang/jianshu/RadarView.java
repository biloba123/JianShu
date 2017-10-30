package com.lvqingyang.jianshu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/10/27
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class RadarView extends View {
    private int mCount=6;
    private float mAngle = 360/mCount;
    private float mAngleRad = (float) (Math.PI*2/mCount);
    private float mRadius;
    private float mWith, mHeight;
    private Paint mMainPaint;
//    private Bitmap mBitmap;
    private float mMaxValue=100f;
    private float[] mData=new float[]{
            90, 70, 85, 75, 95, 80
    };
    private Paint mValuePaint;
    private String[] mTitles=new String[]{
            "a", "b", "c", "d", "e", "f"
    };
    private Paint mTextPaint;
    private static final String TAG = "RadarView";

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
//        mBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.spider);
    }

    private void initPaint() {
        mMainPaint=new Paint();
        mMainPaint.setStyle(Paint.Style.STROKE);

        mValuePaint=new Paint();
        mValuePaint.setStrokeCap(Paint.Cap.ROUND);
        mValuePaint.setStyle(Paint.Style.FILL);
        mValuePaint.setColor(Color.BLUE);

        mTextPaint=new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(30);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWith=w;
        mHeight=h;
        mRadius=Math.min(mWith, mHeight)*0.9f/(mCount-1)/2;
        super.onSizeChanged(w, h, oldw, oldh);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWith/2, mHeight/2);
        if (mCount>2) {
            drawPolygon(canvas);

            if (mTitles != null && mTitles.length == mCount) {
                drawTitle(canvas);
            }

            if (mData!=null && mData.length == mCount) {
                drawRegion(canvas);
            }
        }
//        canvas.drawBitmap(mBitmap, mBitmap.getWidth()/2f*-1,  mBitmap.getHeight()/2f*-1, mMainPaint);

    }

    private void drawPolygon(Canvas canvas) {
        Path path=new Path();
        for (int i = 1; i < mCount; i++) {
            path.setLastPoint(mRadius*i, 0);
            for (int i1 = 1; i1 < mCount; i1++) {
                float x= (float) (mRadius*i*Math.cos(mAngleRad *i1));
                float y= (float) (mRadius*i*Math.sin(mAngleRad *i1));
                path.lineTo(x, y);
            }
            path.close();//闭合路径
            canvas.drawPath(path, mMainPaint);
            path.reset();//重置
        }

        float maxRadius=mRadius*(mCount-1);
        for (int i = 0; i < mCount; i++) {
            canvas.rotate(mAngle);
            canvas.drawLine(0, 0, maxRadius, 0, mMainPaint);
        }

    }

    private void drawRegion(Canvas canvas) {
        float maxRadius=mRadius*(mCount-1);
        Path path=new Path();//绘制区域

        mValuePaint.setStrokeWidth(20);
        for (int i = 0; i < mCount; i++) {
            float len=mData[i]/mMaxValue*maxRadius;
            float x= (float) (len*Math.cos(mAngleRad *i));
            float y= (float) (len*Math.sin(mAngleRad *i));

            canvas.drawPoint(x, y, mValuePaint);

            if (i==0) {
                path.setLastPoint(x, y);
            }else {
                path.lineTo(x, y);
            }
        }

        path.close();
        mValuePaint.setAlpha(127);
        canvas.drawPath(path, mValuePaint);
    }

    private void drawTitle(Canvas canvas) {
        float maxRadius=mRadius*(mCount-1);
        Paint.FontMetrics fontMetrics=mTextPaint.getFontMetrics();
        float fontHeight=fontMetrics.bottom- fontMetrics.top;
        for (int i = 0; i < mCount; i++) {
            float x= (float) (maxRadius*Math.cos(mAngleRad *i));
            float y= (float) (maxRadius*Math.sin(mAngleRad *i));
            float left, top, right, bottom;
            if (x>0) {
                left=x;
                top=y-fontHeight;

            }
        }
    }
}
