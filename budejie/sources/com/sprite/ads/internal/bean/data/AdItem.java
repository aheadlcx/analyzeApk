package com.sprite.ads.internal.bean.data;

import com.sprite.ads.ADType;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.JsonInterface;
import com.sprite.ads.internal.utils.c;
import java.io.Serializable;
import org.json.JSONObject;

public abstract class AdItem implements JsonInterface, Serializable {
    public ADType adType;
    public DataSourceType dataSourceType;
    public int downwarn;
    private boolean isDownListener = true;
    protected boolean isInterceptTouchEvent = true;
    protected boolean isLoadNextAd = true;
    public int position;
    public String postId;
    public int refreshTime;
    public String target;

    public DataSourceType getDataSourceType() {
        return this.dataSourceType;
    }

    public abstract String getDesc();

    protected double getDouble(String str, JSONObject jSONObject) {
        return c.c(jSONObject, str);
    }

    public int getHeight() {
        return -1;
    }

    protected int getInt(String str, JSONObject jSONObject) {
        return c.b(jSONObject, str);
    }

    public boolean getInterceptTouchEvent() {
        return this.isInterceptTouchEvent;
    }

    public boolean getIsDownListener() {
        return this.isDownListener;
    }

    public boolean getIsLoadNextAd() {
        return this.isLoadNextAd;
    }

    public abstract String getMovie();

    public abstract String getPic();

    public abstract String getResType();

    protected String getString(String str, JSONObject jSONObject) {
        return c.a(jSONObject, str);
    }

    public abstract String getTitle();

    public abstract String getUrl();

    public int getWidth() {
        return -1;
    }

    public void setAdType(ADType aDType) {
        this.adType = aDType;
    }

    public void setAdType(String str) {
        setAdType(ADType.valueOf(str.toUpperCase()));
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public void setIsDownListener(boolean z) {
        this.isDownListener = z;
    }

    public String toString() {
        return super.toString() + "@ postId:" + this.postId + "," + "adType:" + this.adType + "dataSourceType:" + getDataSourceType();
    }
}
