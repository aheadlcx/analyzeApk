package cn.v6.sixrooms.bean;

import java.io.Serializable;

public class RepertoryBean implements Serializable {
    private static final long serialVersionUID = 1;
    private String gifTotal;
    private String giftID;

    public RepertoryBean(String str, String str2) {
        this.giftID = str;
        this.gifTotal = str2;
    }

    public String getGiftID() {
        return this.giftID;
    }

    public void setGiftID(String str) {
        this.giftID = str;
    }

    public String getGifTotal() {
        return this.gifTotal;
    }

    public void setGifTotal(String str) {
        this.gifTotal = str;
    }

    public String toString() {
        return "RepertoryBean [giftID=" + this.giftID + ", gifTotal=" + this.gifTotal + "]";
    }
}
