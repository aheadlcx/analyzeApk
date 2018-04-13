package cn.v6.sixrooms.room.bean;

import java.io.Serializable;

public class WeiBoForwardPicBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String smpich = "";
    private String smpicw = "";
    private String url = "";

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getSmpicw() {
        return this.smpicw;
    }

    public void setSmpicw(String str) {
        this.smpicw = str;
    }

    public String getSmpich() {
        return this.smpich;
    }

    public void setSmpich(String str) {
        this.smpich = str;
    }

    public String toString() {
        return "WeiBoForwardPicBean [url=" + this.url + ", smpicw=" + this.smpicw + ", smpich=" + this.smpich + "]";
    }
}
