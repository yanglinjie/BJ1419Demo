package com.qianfeng.bj1419demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qianfeng.bj1419demo.utils.FileUtils;

import java.io.File;

/**
 * 重要知识点.1.调用系统相机获取图片
 * 2.调用系统相机指定文件
 * 文件操作 联网 -数据库'
 *
 *
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button photoBtn, photoBtn1, updateBtn;
    private ImageView imgIv;
    public static final int REQUEST_RESULT_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgIv = (ImageView) this.findViewById(R.id.show_image);
        photoBtn = (Button) this.findViewById(R.id.photo_btn);
        photoBtn1 = (Button) this.findViewById(R.id.photo_btn1);
        updateBtn = (Button) this.findViewById(R.id.photo_update);
        photoBtn.setOnClickListener(this);
        photoBtn1.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESULT_CODE && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            if (bitmap != null) {
                imgIv.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_btn:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_RESULT_CODE);
                break;
            case R.id.photo_btn1:
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = FileUtils.getImageFile();
                if (file != null) {
                    Uri uri = Uri.fromFile(file);
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivity(intent1);
                } else {
                    Log.i("-----", "文件创建失败!!!");
                }
                break;
            case R.id.photo_update:
                startActivity(new Intent(this, updateActivity.class));
                break;


        }

    }
}
