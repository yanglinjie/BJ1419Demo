package com.qianfeng.bj1419demo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Package com.qianfeng.bj1419demo.utils
 * @作 用:
 * @创 建 人: zhangwei
 * @日 期: 15/1/20
 * @修 改 人:
 * @日 期:
 */
public class ImageUtils {
    /**
     * @param path
     * @param outWidth
     * @param outHeight
     * @return
     */
    public static Bitmap inSimapSize(String path, int outWidth, int outHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int width = options.outWidth;
        int height = options.outHeight;
        options.inJustDecodeBounds = true; // 解码不放进内存中
        int inSampleSize = 1;
        // 当前的图片的宽或者高 大于 > 指定压缩的高度, 就不停压缩
        while ((width / inSampleSize) > outWidth || (height / inSampleSize) > outHeight) {
            inSampleSize *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 图片压缩 三种方式
     * <p/>
     * 1.第一种 质量压缩法
     * 2.第二种 像素压缩
     * 3.第三种 等比例压缩
     */


    /**
     * 质量压缩法
     *
     * @param bmp
     * @param file
     */
    public static File compressBmpToFile(Bitmap bmp, File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 80;//个人喜欢从80开始,
        bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            options -= 10;
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("文件长度", "----------->>" + file.length());
        return file;


    }


}
