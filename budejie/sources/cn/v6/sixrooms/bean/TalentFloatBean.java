package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class TalentFloatBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String show;
    private String title;

    public String getShow() {
        return this.show;
    }

    public void setShow(String str) {
        this.show = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "TalentFloatBean [show=" + this.show + ", title=" + this.title + "]";
    }
}
