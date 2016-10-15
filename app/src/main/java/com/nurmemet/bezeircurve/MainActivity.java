package com.nurmemet.bezeircurve;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mView = findViewById(R.id.cubic_beizers);
        mView.setBackground(new BezeirCurveDrawable());

    }

    public void toAnim(View view) {
        Intent it = new Intent(this, AnimAct.class);
        startActivity(it);
    }


}
