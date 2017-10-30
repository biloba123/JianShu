package com.lvqingyang.jianshu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CanvasActivity extends AppCompatActivity {

    private PieView pieview;
    private android.widget.EditText ettel;
    private android.widget.RadioButton rbcontral1;
    private android.widget.RadioButton rbcontral2;
    private android.widget.RadioGroup rg;
    private CubicBezier cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
//        this.cb = (CubicBezier) findViewById(R.id.cb);
//        this.rg = (RadioGroup) findViewById(R.id.rg);
//        this.rbcontral2 = (RadioButton) findViewById(R.id.rb_contral2);
//        this.rbcontral1 = (RadioButton) findViewById(R.id.rb_contral1);
//
//        rg.check(R.id.rb_contral1);
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                cb.setMode(checkedId==R.id.rb_contral1);
//            }
//        });


//        this.ettel = (EditText) findViewById(R.id.et_tel);
//        ettel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

//        this.pieview = (PieView) findViewById(R.id.pie_view);
//
//        List<PieView.PieData> pieDataList=new ArrayList<>();
//        pieDataList.add(new PieView.PieData("Java", 20));
//        pieDataList.add(new PieView.PieData("Android", 50));
//        pieDataList.add(new PieView.PieData("PHP", 9));
//        pieDataList.add(new PieView.PieData(".NET", 8));
//        pieDataList.add(new PieView.PieData("FE", 7));
//
//
//        pieview.setPieDataList(pieDataList);
    }

//    private float mStartX, mStartY;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:{
//                mStartX=event.getX();
//                mStartY=event.getY();
//                break;
//            }
//            case MotionEvent.ACTION_MOVE:{
//
//                break;
//            }
//            case MotionEvent.ACTION_UP:{
//                double distance= Math.sqrt(Math.pow(event.getX()-mStartX, 2)+
//                        Math.pow(event.getY()-mStartY, 2));
//                if (distance>= ViewConfiguration.get(this).getScaledTouchSlop()) {
//                    finish();
//                }
//                break;
//            }
//            default:{
//                break;
//            }
//        }
//
//        return super.onTouchEvent(event);
//    }
//
//    private static final String TAG = "CanvasActivity";
//
//    public static void start(Context context) {
//        Intent starter = new Intent(context, CanvasActivity.class);
////        starter.putExtra();
//        context.startActivity(starter);
//    }
}
