package cn.v6.sixrooms.bean;

import java.util.Map;

public class ConfigureInfoBean {
    private String banzou;
    private String game;
    private Map<String, String> giftResMap;
    private String gps;
    private String h5flag;
    private String livesize;

    public String getGame() {
        return this.game;
    }

    public void setGame(String str) {
        this.game = str;
    }

    public String getBanzou() {
        return this.banzou;
    }

    public void setBanzou(String str) {
        this.banzou = str;
    }

    public String getGps() {
        return this.gps;
    }

    public void setGps(String str) {
        this.gps = str;
    }

    public String getLivesize() {
        return this.livesize;
    }

    public void setLivesize(String str) {
        this.livesize = str;
    }

    public String getH5flag() {
        return this.h5flag;
    }

    public Map<String, String> getGiftResMap() {
        return this.giftResMap;
    }

    public void setGiftResMap(Map<String, String> map) {
        this.giftResMap = map;
    }

    public String toString() {
        return "ConfigureInfoBean{gps='" + this.gps + '\'' + ", livesize='" + this.livesize + '\'' + ", banzou='" + this.banzou + '\'' + '}';
    }
}
