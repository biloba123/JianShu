package com.lvqingyang.jianshu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Main5Activity extends AppCompatActivity {

    private static final String TAG = "Activity";
    private MyView view;
    private MyFrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        this.container = (MyFrameLayout) findViewById(R.id.container);
        this.view = (MyView) findViewById(R.id.view);

        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (BuildConfig.DEBUG) Log.d(TAG, "ViewGroup onTouch: ");
                return false;
            }
        });

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuildConfig.DEBUG) Log.d(TAG, "ViewGroup onClick: ");
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (BuildConfig.DEBUG) Log.d(TAG, "View onTouch: ");
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuildConfig.DEBUG) Log.d(TAG, "View onClick: ");
//                CanvasActivity.start(Main5Activity.this);
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                if (BuildConfig.DEBUG) Log.d(TAG, "dispatchTouchEvent: ACTION_DOWN");
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                if (BuildConfig.DEBUG) Log.d(TAG, "dispatchTouchEvent: ACTION_MOVE");
                break;
            }
            case MotionEvent.ACTION_UP:{
                if (BuildConfig.DEBUG) Log.d(TAG, "dispatchTouchEvent: ACTION_UP");
                break;
            }
            default:{
                break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
