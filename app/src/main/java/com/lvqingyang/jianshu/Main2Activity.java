package com.lvqingyang.jianshu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {

    private android.widget.LinearLayout llmenu;
    private android.widget.ImageView ivmenu;
    private android.widget.LinearLayout llcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.llcontent = (LinearLayout) findViewById(R.id.ll_content);
        this.ivmenu = (ImageView) findViewById(R.id.iv_menu);
        this.llmenu = (LinearLayout) findViewById(R.id.ll_menu);

        final float pix=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, getResources().getDisplayMetrics());
        ivmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator oa1=ObjectAnimator.ofFloat(llcontent,"translationX",0,pix);
                ObjectAnimator oa2=ObjectAnimator.ofFloat(llmenu,"translationX",0,pix);
                AnimatorSet set=new AnimatorSet();
                set.play(oa1)
                        .with(oa2);
                set.setDuration(1000);
                set.start();

                new AlertDialog.Builder(Main2Activity.this)
                        .setTitle(R.string.app_name)
                        .create()
                        .show();
            }
        });
    }

    private static final String TAG = "Main2Activity";

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.DEBUG) Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (BuildConfig.DEBUG) Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (BuildConfig.DEBUG) Log.d(TAG, "onStop: ");
    }
}
