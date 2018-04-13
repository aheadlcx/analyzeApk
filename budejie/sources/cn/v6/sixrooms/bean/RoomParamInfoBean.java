package cn.v6.sixrooms.bean;

import java.io.Serializable;
import java.util.Map;

public class RoomParamInfoBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String bgurl;
    private String fans_num;
    private String privnote;
    private String pubchat;
    private int setranking;
    private Map<String, SofaBean> sofa;

    public Map<String, SofaBean> getSofa() {
        return this.sofa;
    }

    public void setSofa(Map<String, SofaBean> map) {
        this.sofa = map;
    }

    public String getFans_num() {
        return this.fans_num;
    }

    public void setFans_num(String str) {
        this.fans_num = str;
    }

    public int getSetranking() {
        return this.setranking;
    }

    public void setSetranking(int i) {
        this.setranking = i;
    }

    public String getPrivnote() {
        return this.privnote;
    }

    public void setPrivnote(String str) {
        this.privnote = str;
    }

    public String getPubchat() {
        return this.pubchat;
    }

    public void setPubchat(String str) {
        this.pubchat = str;
    }

    public void setBGURL(String str) {
        this.bgurl = str;
    }

    public String getBGURL() {
        return this.bgurl;
    }

    public String toString() {
        return "RoomParamInfoBean [privnote=" + this.privnote + ", pubchat=" + this.pubchat + ", bgurl=" + this.bgurl + ", setranking=" + this.setranking + "]";
    }
}
