package com.lvqingyang.jianshu;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class ConflictActivity extends AppCompatActivity {

    private android.widget.Button btnshowwindow;
    private Button btnstartactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflict);
        this.btnstartactivity = (Button) findViewById(R.id.btn_start_activity);
        this.btnshowwindow = (Button) findViewById(R.id.btn_show_window);
        btnshowwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFloatingWindow();
            }
        });

        btnstartactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConflictActivity.this, ConflictActivity.class));
            }
        });

    }

    private void createFloatingWindow() {
        View view = getLayoutInflater().inflate(R.layout.floating_window_layout, null);
        final LayoutParams params = new LayoutParams(
                200,
                200,
                LayoutParams.TYPE_APPLICATION_OVERLAY,
                LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.START | Gravity.TOP;
        params.x = 100;
        params.y = 100;
        getWindowManager().addView(view, params);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    params.x = (int) event.getRawX() - 100;
                    params.y = (int) event.getRawY() - 100;
                    getWindowManager().updateViewLayout(v, params);
                    return true;
                }
                return false;
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                getWindowManager().removeViewImmediate(v);
                return true;
            }
        });
    }
}
