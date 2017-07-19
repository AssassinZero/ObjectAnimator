package com.example.administrator.objectanimator;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private GestureDetector mGestureDetector;
    private int windowWidth;
    private int windowHeight;
    private ImageView img;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.获取窗口管理器
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//2.获取默认显示设备 或称之为 得到当前屏幕的显示器对象
        Display display = wm.getDefaultDisplay();
//3.创建一个Point点对象用来接收屏幕尺寸信息
        Point point =new Point();
//4.Point点对象接收当前设备屏幕尺寸信息
        display.getSize(point);
//从Point点对象中获取屏幕的宽度(单位像素)
        windowWidth = point.x;
//从Point点对象中获取屏幕的高度(单位像素)
        windowHeight = point.y;

        setContentView(R.layout.activity_main);
//        img = (ImageView) findViewById(R.id.img);
//        ImageViewOnMultiTouchListener listener=new ImageViewOnMultiTouchListener();
//        img.setOnTouchListener(listener);
        mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
        btn = (Button) findViewById(R.id.btn);
        btn.setOnTouchListener(new OnTouchListener() {
            int[] temp = new int[] { 0, 0 };

            public boolean onTouch(View v, MotionEvent event) {

                int eventaction = event.getAction();

                int x = (int) event.getRawX();
                int y = (int) event.getRawY();

                switch (eventaction) {

                    case MotionEvent.ACTION_DOWN: // touch down so check if the
                        temp[0] = (int) event.getX();
                        temp[1] = y - v.getTop();
                        break;

                    case MotionEvent.ACTION_MOVE: // touch drag with the ball
                        int ty = y - temp[1];
                        int by = y - temp[1] + v.getHeight();
                        if (ty<0){
                            ty=0;
                        }else if (ty>windowHeight-v.getHeight()){
                            ty = windowHeight-v.getHeight();
                        }
                        if (by<v.getHeight()){
                            by=v.getHeight();
                        }else if (by>windowHeight){
                            by = windowHeight;
                        }
                        v.layout(windowWidth-v.getWidth()-5,ty,windowWidth-5, by);

//                  v.postInvalidate();
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                mGestureDetector.onTouchEvent(event);
                return true;
            }

        });


    }

    private String getActionName(int action){
        String name = "";
        switch (action){
            case MotionEvent.ACTION_DOWN:
                name = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                name = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                name = "ACTION_UP";
                break;
        }
        return name;
    }

    /**
     *  onSingleTapUp被调用，说明发生了单击事件，onSingleTapConfirmed被调用，说明确认发生了一个单击事件，
     *  不是双击的事件。需要注意的是onSingleTapUp已经是一click事件，onSingleTapUp触发的时候是ACTION_UP事件。
     *  onSingleTapConfirmed是在用户手指离开屏幕后触发，所有up并不是所有触屏事件的结束。
     *  onLongPress实在ACTION_DOWN时发生，onLongPress发生后在up之前不会用其他事件触发，可以在onShowPress处理状态的改变，比如按钮的按下状态。
     *  onScroll事件是拖动，onFling是抛。结合log来了解一下。首先是ACTION_DOWN，之后多次ACTION_MOVE，移动超过一定距离，触发了onScroll，
     *  如果onScroll被触发，在up之前是不会有长按，单击，双击等事件的。看一下onScroll的参数
     *  onFling的前两个参数和onScroll相同，e2为用户拖动完离开屏幕时的点。veloctiyX，velocitY为离开屏幕时的初始速度，
     *  以这两个速度为初始速度做匀减速运动，就是现在拖动列表和拖动图片的各种缓存滚动的效果。
     *  除了onLongPress，这些函数都是有返回值的，这些返回值会通过 mGestureDetector.onTouchEvent(event); 传递给onTouch。
     */
    class MyOnGestureListener extends SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Toast.makeText(MainActivity.this,"联系客服",Toast.LENGTH_SHORT).show();
            String url="mqqwpa://im/chat?chat_type=wpa&uin=275108586";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }
    }
}
