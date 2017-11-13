package com.lvqingyang.jianshu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    private SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.sv = (SearchView) findViewById(R.id.sv);

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sv.getState()== SearchView.State.NONE) {
                    sv.startSearch();
                }else if (sv.getState()== SearchView.State.SEARCHING){
                    sv.stopSearch();
                }
            }
        });
    }
}
