package com.example.administrator.objectanimator;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/19.
 */
public class AnimatorTextView extends Button {

    public AnimatorTextView(Context context) {
        super(context);
    }

    public AnimatorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
