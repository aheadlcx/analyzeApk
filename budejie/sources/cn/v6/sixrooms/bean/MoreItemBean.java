package cn.v6.sixrooms.bean;

import android.graphics.drawable.Drawable;

public class MoreItemBean {
    int menuFlag;
    Drawable pic;
    String text;

    public int getMenuFlag() {
        return this.menuFlag;
    }

    public void setMenuFlag(int i) {
        this.menuFlag = i;
    }

    public MoreItemBean(String str, Drawable drawable) {
        this.text = str;
        this.pic = drawable;
    }

    public MoreItemBean(String str, Drawable drawable, int i) {
        this.text = str;
        this.pic = drawable;
        this.menuFlag = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Drawable getPic() {
        return this.pic;
    }

    public void setPic(Drawable drawable) {
        this.pic = drawable;
    }

    public String toString() {
        return "MoreItemBean [text=" + this.text + ", pic=" + this.pic + "]";
    }
}
