package cn.v6.sixrooms.bean;

import android.text.TextUtils;
import java.io.Serializable;

public class LiveinfoBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String allgetnum;
    private String flvAdrr;
    private String flvtitle;
    private String largepic;
    private String recordtype;
    private String secflvtitle;
    private String spredPic;
    private String starttime;
    private String title;
    private String videotype;

    public String getSpredPic() {
        if ("null".equals(this.spredPic)) {
            this.spredPic = null;
        }
        if (!TextUtils.isEmpty(this.spredPic)) {
            this.spredPic = this.spredPic.replace("_b.", "_s.");
        }
        return this.spredPic;
    }

    public void setSpredPic(String str) {
        this.spredPic = str;
    }

    public String getLargepic() {
        return this.largepic;
    }

    public void setLargepic(String str) {
        this.largepic = str;
    }

    public String getVideotype() {
        return this.videotype;
    }

    public void setVideotype(String str) {
        this.videotype = str;
    }

    public String getRecordtype() {
        return this.recordtype;
    }

    public void setRecordtype(String str) {
        this.recordtype = str;
    }

    public String getFlvtitle() {
        return this.flvtitle;
    }

    public void setFlvtitle(String str) {
        this.flvtitle = str;
    }

    public String getSecflvtitle() {
        return this.secflvtitle;
    }

    public void setSecflvtitle(String str) {
        this.secflvtitle = str;
    }

    public String getFlvAdrr() {
        return this.flvAdrr;
    }

    public void setFlvAdrr(String str) {
        this.flvAdrr = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setStarttime(String str) {
        this.starttime = str;
    }

    public String getAllgetnum() {
        return this.allgetnum;
    }

    public void setAllgetnum(String str) {
        this.allgetnum = str;
    }

    public String toString() {
        return "LiveinfoBean{title='" + this.title + '\'' + ", starttime='" + this.starttime + '\'' + ", allgetnum='" + this.allgetnum + '\'' + ", secflvtitle='" + this.secflvtitle + '\'' + ", flvtitle='" + this.flvtitle + '\'' + ", flvAdrr='" + this.flvAdrr + '\'' + ", recordtype='" + this.recordtype + '\'' + ", videotype='" + this.videotype + '\'' + ", largepic='" + this.largepic + '\'' + ", spredPic='" + this.spredPic + '\'' + '}';
    }
}
