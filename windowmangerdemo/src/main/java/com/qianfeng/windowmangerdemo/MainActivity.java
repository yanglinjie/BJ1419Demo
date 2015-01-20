package com.qianfeng.windowmangerdemo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qianfeng.windowmangerdemo.widget.FloatView;

/**
 * WindowManager
 * 第一个 addViw();
 * 第二个 removeView();
 * 第二个 updateLayout();
 */
public class MainActivity extends ActionBarActivity {
    private WindowManager windowManager;
    FloatView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        view = new FloatView(getApplicationContext());
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //这个View在屏幕上初始化显示的位置
        params.x = 100;
        params.y = 300;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        windowManager.addView(view, params);
        view.setWindowManager(windowManager);
        view.setParams(params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        windowManager.removeView(view);
    }
}
