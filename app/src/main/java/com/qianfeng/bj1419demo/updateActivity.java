package com.qianfeng.bj1419demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.qianfeng.bj1419demo.adapter.ShowImageAdapter;
import com.qianfeng.bj1419demo.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 初始化ui
 */
public class updateActivity extends ActionBarActivity {
    private GridView gridView;
    private ShowImageAdapter adapter;
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private BitmapFactory bitmapFactory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        gridView = (GridView) this.findViewById(R.id.update_gridview);
        adapter = new ShowImageAdapter(bitmaps, this);
        gridView.setAdapter(adapter);
    }

    /**
     * 第一步 获得保存图片的文件夹
     * 第二步 遍历保存图片的文件 获得保存的图片
     * 第三步 讲文件转化bitmap ----  BitmapFactory
     * 第四步 显示图片
     */

    /**
     * 获取文件下的图片
     */
    private void initData() {
        //获得保存图片的文件夹
        File imageCache = FileUtils.getImageCache();
        File[] imageFiles = imageCache.listFiles();
        if (imageFiles != null && imageFiles.length > 0) {
            for (File imageFile : imageFiles) {
                if (imageFile.isFile() && imageFile.getName().endsWith(".png")) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                    if (bitmap != null && bitmap.getByteCount() > 0) {
                        bitmaps.add(bitmap);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update, menu);
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
}
