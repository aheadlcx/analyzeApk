package cn.v6.sixrooms.bean;

import android.graphics.drawable.Drawable;

public class SmileyVo {
    private String faceName;
    private String fileName;
    private int id;
    private Drawable mSourceDrawabl;
    private int resID;
    private int type;

    public SmileyVo(String str, String str2) {
        this.faceName = str;
        this.fileName = str2;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public Drawable getmSourceDrawabl() {
        return this.mSourceDrawabl;
    }

    public void setmSourceDrawabl(Drawable drawable) {
        this.mSourceDrawabl = drawable;
    }

    public String getFaceName() {
        return this.faceName;
    }

    public void setFaceName(String str) {
        this.faceName = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getResID() {
        return this.resID;
    }

    public void setResID(int i) {
        this.resID = i;
    }

    public String toString() {
        return "SmileyVo [id=" + this.id + ", mSourceDrawabl=" + this.mSourceDrawabl + ", faceName=" + this.faceName + ", type=" + this.type + ", resID=" + this.resID + ", fileName=" + this.fileName + "]";
    }
}
