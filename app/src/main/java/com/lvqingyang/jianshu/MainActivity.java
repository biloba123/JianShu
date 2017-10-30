package com.lvqingyang.jianshu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "MainActivity";private TouchView tvblue;private TouchView tvgreen;private TouchView tvred;private TouchView tvorange;

    private float numO,numR,numG,numB;
    boolean isFirst=true;
    private MyButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btn = (MyButton) findViewById(R.id.btn);
        this.tvorange = (TouchView) findViewById(R.id.tv_orange);
        this.tvred = (TouchView) findViewById(R.id.tv_red);
        this.tvgreen = (TouchView) findViewById(R.id.tv_green);
        this.tvblue = (TouchView) findViewById(R.id.tv_blue);
        
        tvblue.setOnTouchListener(this);
        tvorange.setOnTouchListener(this);
        tvgreen.setOnTouchListener(this);
        tvred.setOnTouchListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BuildConfig.DEBUG) Log.d(TAG, "onClick: ");
                btn.smoothScrollBy(-50,-100,new Random().nextInt(10000));
            }
        });

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float rawY=motionEvent.getRawY();
        if (isFirst){
            numG=numB=numR=numO=rawY;
            isFirst=false;
        }
        switch (view.getId()) {
            case R.id.tv_blue:{
                numB=rawY;
                break;
            }
            case R.id.tv_green:{
                numG= rawY;
                break;
            }
            case R.id.tv_orange:{
                numO= rawY;
                break;
            }
            case R.id.tv_red:{
                numR= rawY;
                break;
            }
        }

        sort();

        return false;
    }

    private void sort(){
        float[] numArr={numB,numG,numO,numR};
        TouchView[] tvArr={tvblue,tvgreen,tvorange,tvred};

        int len=numArr.length;
        float temp;
        TouchView tempTv;

        for(int i=0;i<len-1;i++){
            for(int j=0;j<len-i-1;j++){
                if(numArr[j]>numArr[j+1]){
                    temp=numArr[j];
                    numArr[j]=numArr[j+1];
                    numArr[j+1]=temp;

                    tempTv=tvArr[j];
                    tvArr[j]=tvArr[j+1];
                    tvArr[j+1]=tempTv;
                }
            }
        }

        for (int i = 0; i < tvArr.length; i++) {
            tvArr[i].setText(i+1+"");
        }
    }
}
