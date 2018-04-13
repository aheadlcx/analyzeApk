package cn.v6.sixrooms.bean;

public class GiftUpdateBean {
    private String down;
    private String md5;
    private String patchDown;
    private String type;

    public String getMd5() {
        return this.md5;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public String getDown() {
        return this.down;
    }

    public void setDown(String str) {
        this.down = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getPatchDown() {
        return this.patchDown;
    }

    public void setPatchDown(String str) {
        this.patchDown = str;
    }

    public String toString() {
        return "GiftUpdateBean [down=" + this.down + ", type=" + this.type + ", patchDown=" + this.patchDown + "]";
    }
}
