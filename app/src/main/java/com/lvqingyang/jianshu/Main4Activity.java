package com.lvqingyang.jianshu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

public class Main4Activity extends AppCompatActivity {

    private SampleFragment mSampleFragment;
    private FragmentManager manager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    manager.beginTransaction()
                            .attach(mSampleFragment)
                            .commit();
                    return true;
                case R.id.navigation_dashboard:
                    manager.beginTransaction()
                            .detach(mSampleFragment)
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    manager.beginTransaction()
                            .detach(mSampleFragment)
                            .commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        manager=getSupportFragmentManager();
        if (savedInstanceState != null) {
            mSampleFragment = (SampleFragment) manager.findFragmentByTag(SampleFragment.class.getName());
            manager.beginTransaction()
                    .attach(mSampleFragment)
                    .commit();
        }else {
            mSampleFragment = SampleFragment.newInstance(1);
            manager.beginTransaction()
                    .add(R.id.container, mSampleFragment)
                    .commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

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

    private static final String TAG = "Main4Activity";

}
