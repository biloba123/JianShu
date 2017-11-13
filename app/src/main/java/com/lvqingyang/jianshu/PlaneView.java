package com.lvqingyang.jianshu;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2017/11/7
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class PlaneView extends View {
    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    private Paint mPaint;
    private Path mPath;


    public PlaneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        pos=new float[2];
        tan=new float[2];
//        BitmapFactory.Options options=new BitmapFactory.Options();
//        options.inSampleSize=2;
        mBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.plane);
        mMatrix=new Matrix();

        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        mPath=new Path();
        mPath.cubicTo(100, 3, 1000, 600, 500, 900);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.translate(getMeasuredWidth()/2, getMeasuredHeight()/2);
        canvas.drawPath(mPath, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

    public void startAnim(){
        final PathMeasure measure=new PathMeasure(mPath, false);
        ValueAnimator animator=ValueAnimator.ofFloat(0f, 1f)
                .setDuration(4000);
        final float p1=measure.getLength();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance=p1*((float)animation.getAnimatedValue());
                measure.getPosTan(distance, pos, tan);
                float degree= (float) (Math.atan2(tan[1], tan[0])*180/Math.PI);
                measure.getMatrix(distance, mMatrix, PathMeasure.POSITION_MATRIX_FLAG|PathMeasure.TANGENT_MATRIX_FLAG);
                mMatrix.preTranslate(-mBitmap.getWidth()/2, -mBitmap.getHeight()/2);
                //                mMatrix.reset();
//                mMatrix.postRotate(degree, mBitmap.getWidth()/2, mBitmap.getHeight()/2);
//                mMatrix.postTranslate(pos[0]-mBitmap.getWidth()/2,
//                        pos[1]-mBitmap.getHeight()/2);
                postInvalidate();
            }
        });
        animator.start();
    }
}
