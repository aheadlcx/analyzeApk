package com.budejie.www.bean;

import com.budejie.www.activity.plate.bean.PlateBean;
import java.util.ArrayList;

public class DraftBean {
    private static final String TAG = "DraftBean";
    public String bimage;
    public int bvoiceid;
    public String content;
    public String createTime;
    public String height;
    public int id;
    public boolean isGif;
    public boolean isWatermark;
    public String landuri;
    public String linkurl;
    public String plateDataStr;
    public ArrayList<PlateBean> plateDatas;
    public String reserve;
    public int state;
    public int theme_id;
    public String theme_name;
    public int theme_type;
    public String tid;
    public int uid;
    public String video;
    public int videotime;
    public String voice;
    public int voicetime;
    public VoteData voteData;
    public String voteDataStr;
    public String width;

    public DraftBean(int i, int i2, int i3, String str, String str2, String str3, int i4, int i5, String str4, String str5, String str6, int i6, boolean z) {
        this.id = i;
        this.uid = i2;
        this.state = i3;
        this.bimage = str;
        this.voice = str2;
        this.content = str3;
        this.bvoiceid = i4;
        this.voicetime = i5;
        this.createTime = str4;
        this.reserve = str5;
        this.video = str6;
        this.videotime = i6;
        this.isWatermark = z;
    }

    public void setWidth(String str) {
        this.width = str;
    }

    public String getWidth() {
        return this.width;
    }

    public void setHeight(String str) {
        this.height = str;
    }

    public String getHeight() {
        return this.height;
    }

    public String getLinkurl() {
        return this.linkurl;
    }

    public void setLinkurl(String str) {
        this.linkurl = str;
    }
}
