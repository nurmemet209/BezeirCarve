package com.nurmemet.bezeircurve;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nurmemet on 10/16/2016.
 */

public class AnimAct extends Activity {
    private View startView;
    private View endView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_anim);
        startView = findViewById(R.id.start_);
        endView = findViewById(R.id.end_);

    }

    public void onThrow(View view) {
        int[] start = new int[2];
        startView.getLocationInWindow(start);
        int[] end = new int[2];
        endView.getLocationInWindow(end);
        ThrowAnim mThrower = new ThrowAnim(this, (ViewGroup) findViewById(android.R.id.content), new PointF(end[0], end[1]), new PointF(start[0], start[1]));
        mThrower.move();
    }
}
