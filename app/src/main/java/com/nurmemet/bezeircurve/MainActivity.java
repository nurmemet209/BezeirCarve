package com.nurmemet.bezeircurve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
        private View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView=findViewById(R.id.cubic_beizers);
        mView.setBackground(new BezeirCurveDrawable());
    }
}
