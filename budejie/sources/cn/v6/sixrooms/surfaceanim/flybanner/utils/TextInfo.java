package cn.v6.sixrooms.surfaceanim.flybanner.utils;

public class TextInfo {
    protected String backStr;
    protected String frontStr;
    protected boolean isNewLine = true;

    public boolean isNewLine() {
        return this.isNewLine;
    }

    public void setNewLine(boolean z) {
        this.isNewLine = z;
    }

    public String getFrontStr() {
        return this.frontStr;
    }

    public void setFrontStr(String str) {
        this.frontStr = str;
    }

    public String getBackStr() {
        return this.backStr;
    }

    public void setBackStr(String str) {
        this.backStr = str;
    }
}
