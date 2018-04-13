package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class ChangzhanStatusMsgBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String atline;
    private String title;
    private String value;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getAtline() {
        return this.atline;
    }

    public void setAtline(String str) {
        this.atline = str;
    }
}
