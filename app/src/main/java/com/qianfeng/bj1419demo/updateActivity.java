package com.qianfeng.bj1419demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qianfeng.bj1419demo.adapter.ShowImageAdapter;
import com.qianfeng.bj1419demo.bean.UpdateImage;
import com.qianfeng.bj1419demo.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;

/**
 * 初始化ui
 * 图片的二次采样
 */
public class updateActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final String updateUrl = "http://10.0.155.42:8080/app/uplaodFile?method=upload";
    private Button updateBtn;
    private GridView gridView;
    private ShowImageAdapter adapter;
    private ArrayList<UpdateImage> bitmaps = new ArrayList<UpdateImage>();

    private ProgressBar progressBar;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
        }
    };

    /**
     * 数据源
     * 文件
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        View view = getLayoutInflater().inflate(R.layout.titlebar_layout, null);
        updateBtn = (Button) view.findViewById(R.id.titlebar_update_btn);
        updateBtn.setOnClickListener(this);
        getSupportActionBar().setCustomView(view, new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        initView();
        initData();
    }

    /**
     * 初始化控件
     * onitemclicklistener失效
     * 1.检查item checkbox button 这些控件优先获得焦点
     * 2.设置监听在设置Adapter之前
     */
    private void initView() {
        gridView = (GridView) this.findViewById(R.id.update_gridview);
        gridView.setOnItemClickListener(this);
        adapter = new ShowImageAdapter(bitmaps, this);
        gridView.setAdapter(adapter);
    }

    /**
     * 模拟web端 post请求方式 form表单形式
     */

    public void updateImages() {
        /**
         * 选中的图片一张张添加表中
         */
        HttpUtils httpUtils = new HttpUtils();
        RequestParams params = new RequestParams();
        if (bitmaps != null && !bitmaps.isEmpty()) {
            for (int i = 0; i < bitmaps.size(); i++) {
                boolean isCheck = bitmaps.get(i).getIsCheck();
                if (isCheck) {
                    File compressFile = FileUtils.getCompressImageFile(bitmaps.get(i).getBitmap());
                    params.addBodyParameter("" + i, compressFile);
                }
            }

            HttpHandler<String> send = httpUtils.send(HttpRequest.HttpMethod.POST, updateUrl, params, new RequestCallBack<String>() {
                @Override
                public void onStart() {
                    super.onStart();
                    progressBar = new ProgressBar(updateActivity.this);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                    if (isUploading) {
                        progressBar.setMax((int) total);
                    }
                    progressBar.setProgress((int) current);
                }

                @Override
                public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(HttpException e, String s) {

                }
            });
        }


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
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = handler.obtainMessage();
                //获得保存图片的文件夹
                File imageCache = FileUtils.getImageCache();
                File[] imageFiles = imageCache.listFiles();
                if (imageFiles != null && imageFiles.length > 0) {
                    for (File imageFile : imageFiles) {
                        if (imageFile.isFile()) {
                            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                            if (bitmap != null && bitmap.getByteCount() > 0) {
                                UpdateImage updateImage = new UpdateImage(bitmap, imageFile, false);
                                bitmaps.add(updateImage);
                            }
                        }
                    }
                }
                handler.sendMessage(message);
            }
        }).start();
    }

    /**
     * 复用converterview
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击选择,
        //两次点击取消
        ShowImageAdapter.ViewHolder vh = (ShowImageAdapter.ViewHolder) view.getTag();
        if (vh.cb.isChecked()) {
            vh.cb.setChecked(false);
            bitmaps.get(position).setIsCheck(false);
        } else {
            vh.cb.setChecked(true);
            bitmaps.get(position).setIsCheck(true);
        }
    }

    @Override
    public void onClick(View v) {
        updateImages();

    }
}
