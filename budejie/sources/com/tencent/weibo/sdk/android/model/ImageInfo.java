package com.tencent.weibo.sdk.android.model;

import android.graphics.drawable.Drawable;

public class ImageInfo extends BaseVO {
    private static final long serialVersionUID = 2647179822312867756L;
    private Drawable drawable;
    private String imageName;
    private String imagePath;
    private String playPath;

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String str) {
        this.imageName = str;
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getPlayPath() {
        return this.playPath;
    }

    public void setPlayPath(String str) {
        this.playPath = str;
    }
}
