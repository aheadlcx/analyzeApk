package cn.v6.sixrooms.room.gift;

import android.text.TextUtils;

public class Gift$Pic implements Cloneable {
    private String height;
    private String img;
    private String img2x;
    private String img3x;
    final /* synthetic */ Gift this$0;
    private String width;

    public Gift$Pic(Gift gift) {
        this.this$0 = gift;
    }

    public String getImg2x() {
        return !TextUtils.isEmpty(this.img) ? this.img.replace(".png", "@2x.png") : "";
    }

    public String getImg3x() {
        return !TextUtils.isEmpty(this.img) ? this.img.replace(".png", "@3x.png") : "";
    }

    public String getImg() {
        return this.img == null ? "" : this.img;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public void setImg(String str) {
        this.img = str;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String str) {
        this.width = str;
    }

    public Gift$Pic clone() throws CloneNotSupportedException {
        return (Gift$Pic) super.clone();
    }
}
