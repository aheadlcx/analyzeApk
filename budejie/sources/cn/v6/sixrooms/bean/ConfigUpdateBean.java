package cn.v6.sixrooms.bean;

public class ConfigUpdateBean {
    private String down;
    private String fdown;
    private String fmd5;
    private String ftype;
    private String md5;
    private String ndown;
    private String nmd5;
    private String ntype;
    private String patchDown;
    private String pdown;
    private String pmd5;
    private String ptype;
    private String type;

    public String getPdown() {
        return this.pdown;
    }

    public void setPdown(String str) {
        this.pdown = str;
    }

    public String getPtype() {
        return this.ptype;
    }

    public void setPtype(String str) {
        this.ptype = str;
    }

    public String getPmd5() {
        return this.pmd5;
    }

    public void setPmd5(String str) {
        this.pmd5 = str;
    }

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

    public String getFdown() {
        return this.fdown;
    }

    public void setFdown(String str) {
        this.fdown = str;
    }

    public String getFtype() {
        return this.ftype;
    }

    public void setFtype(String str) {
        this.ftype = str;
    }

    public String getFmd5() {
        return this.fmd5;
    }

    public void setFmd5(String str) {
        this.fmd5 = str;
    }

    public String getNdown() {
        return this.ndown;
    }

    public void setNdown(String str) {
        this.ndown = str;
    }

    public String getNtype() {
        return this.ntype;
    }

    public void setNtype(String str) {
        this.ntype = str;
    }

    public String getNmd5() {
        return this.nmd5;
    }

    public void setNmd5(String str) {
        this.nmd5 = str;
    }

    public String toString() {
        return "ConfigUpdateBean{down='" + this.down + '\'' + ", type='" + this.type + '\'' + ", patchDown='" + this.patchDown + '\'' + ", md5='" + this.md5 + '\'' + ", pdown='" + this.pdown + '\'' + ", ptype='" + this.ptype + '\'' + ", pmd5='" + this.pmd5 + '\'' + ", fdown='" + this.fdown + '\'' + ", ftype='" + this.ftype + '\'' + ", fmd5='" + this.fmd5 + '\'' + ", ndown='" + this.ndown + '\'' + ", ntype='" + this.ntype + '\'' + ", nmd5='" + this.nmd5 + '\'' + '}';
    }
}
