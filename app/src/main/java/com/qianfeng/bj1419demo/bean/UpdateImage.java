package com.qianfeng.bj1419demo.bean;

import android.graphics.Bitmap;

import java.io.File;
import java.io.Serializable;

/**
 * @Package com.qianfeng.bj1419demo.bean
 * @作 用:
 * @创 建 人: zhangwei
 * @日 期: 15/1/20
 * @修 改 人:
 * @日 期:
 */
public class UpdateImage implements Serializable {
    private Bitmap bitmap;
    private File imageFile;
    private Boolean isCheck;

    public UpdateImage() {
    }

    public UpdateImage(Bitmap bitmap, File imageFile, Boolean isCheck) {
        this.bitmap = bitmap;
        this.imageFile = imageFile;
        this.isCheck = isCheck;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }
}
