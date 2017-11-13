package com.lvqingyang.jianshu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

public class SvgActivity extends AppCompatActivity {

    private com.eftimoff.androipathview.PathView pathview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        this.pathview = (PathView) findViewById(R.id.path_view);

        pathview.useNaturalColors();
        pathview.setFillAfter(true);
        pathview.getPathAnimator()
                .duration(3000)
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
}
