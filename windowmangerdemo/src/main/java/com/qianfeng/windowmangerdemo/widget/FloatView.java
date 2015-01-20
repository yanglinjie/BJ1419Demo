package com.qianfeng.windowmangerdemo.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qianfeng.windowmangerdemo.R;

/**
 * @Package com.qianfeng.windowmangerdemo.widget
 * @作 用:
 * @创 建 人: zhangwei
 * @日 期: 15/1/20
 * @修 改 人:
 * @日 期:
 */
public class FloatView extends ImageView {
    WindowManager.LayoutParams params;
    WindowManager windowManager;
    private int downX;
    private int downY;

    public FloatView(Context context) {
        super(context);
        init();
    }

    public void setParams(WindowManager.LayoutParams params) {
        this.params = params;
    }


    public void setWindowManager(WindowManager windowManager) {
        this.windowManager = windowManager;
    }

    private void init() {
        setImageResource(R.drawable.ic_launcher);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            downX = (int) event.getRawX();
            downY = (int) event.getRawY();
        } else if (MotionEvent.ACTION_MOVE == event.getAction()) {
            int x = (int) event.getRawX() - downX;
            int y = (int) event.getRawY() - downY;
            updateLayout(x, y);
        } else if (MotionEvent.ACTION_UP == event.getAction()) {

        }
        return super.onTouchEvent(event);
    }

    public void updateLayout(int x, int y) {
        params.x += x;
        params.y += y;
        windowManager.updateViewLayout(this, params);
    }

}
