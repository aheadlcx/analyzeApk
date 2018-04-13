package cn.v6.sixrooms.pay.bean;

import java.io.Serializable;

public class OrderStatusBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String content;
    private String flag;
    private String key;

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String str) {
        this.flag = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String toString() {
        return "OrderStatusBean [flag=" + this.flag + ", content=" + this.content + ", key=" + this.key + "]";
    }
}
