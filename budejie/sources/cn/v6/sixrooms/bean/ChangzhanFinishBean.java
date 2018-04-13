package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class ChangzhanFinishBean extends MessageBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String toname;
    private String touid;

    public String getTouid() {
        return this.touid;
    }

    public void setTouid(String str) {
        this.touid = str;
    }

    public String getToname() {
        return this.toname;
    }

    public void setToname(String str) {
        this.toname = str;
    }
}
