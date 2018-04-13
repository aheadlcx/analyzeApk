package cn.v6.sixrooms.bean;

public class FlagBean extends MessageBean {
    private static final long serialVersionUID = 1;
    private String flag;

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public String toString() {
        return "FlagBean [flag=" + this.flag + "]";
    }
}
