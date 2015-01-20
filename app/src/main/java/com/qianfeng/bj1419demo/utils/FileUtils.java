package com.qianfeng.bj1419demo.utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package com.qianfeng.bj1419demo.utils
 * @作 用:    文件操作工具类
 * @创 建 人: zhangwei
 * @日 期: 15/1/20
 * @修 改 人:
 * @日 期:
 */
public class FileUtils {


    /**
     * 4.0 系统分区,sd卡分区 -- sd卡 -sdk2
     * 小于4.0 就一个系统分区 Sdk
     * 获取应用程序的 根目录
     *
     * @return
     */
    public static File getRootCache() {
        //判断sd卡是否存在
        File appFile = null;
        String path = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory() + File.separator + AppConfig.APP_CACHE;

        } else {
            path = Environment.getDataDirectory() + File.separator + AppConfig.APP_ROOT_CACHE;
        }
        appFile = new File(path);
        if (!appFile.exists()) {
            appFile.mkdirs();
        }
        return appFile;
    }

    /**
     * 获取应用程序的图片保存文件夹
     *
     * @return
     */
    public static File getImageCache() {
        String imagePath = getRootCache().getAbsolutePath() + File.separator + AppConfig.IMAGE_CACHE;
        File imageFile = new File(imagePath);
        if (!imageFile.exists()) {
            imageFile.mkdirs();
        }
        return imageFile;
    }


    /**
     * @return sdk/qianfeng/image/201511111111.png;
     */
    public static File getImageFile() {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
        String imageFilePath = getImageCache().getAbsolutePath() + File.separator + fileName;
        File imageFile = new File(imageFilePath);
        if (!imageFile.exists()) {
            try {
                imageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  imageFile;
    }


}
