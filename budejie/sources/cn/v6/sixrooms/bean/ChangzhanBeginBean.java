package cn.v6.sixrooms.bean;

import java.io.Serializable;
import java.util.List;

public class ChangzhanBeginBean extends MessageBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String duration;
    private List<ChangzhanRaterBean> jury;
    private String toname;
    private String touid;

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

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

    public List<ChangzhanRaterBean> getJury() {
        return this.jury;
    }

    public void setJury(List<ChangzhanRaterBean> list) {
        this.jury = list;
    }

    public String toString() {
        return "ChangzhanBeginBean [duration=" + this.duration + ", touid=" + this.touid + ", toname=" + this.toname + ", jury=" + this.jury + "]";
    }
}
