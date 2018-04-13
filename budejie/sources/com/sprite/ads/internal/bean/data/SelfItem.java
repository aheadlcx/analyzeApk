package com.sprite.ads.internal.bean.data;

import com.sprite.ads.ADType;
import com.sprite.ads.DataSourceType;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class SelfItem extends AdItem {
    public ADType adType;
    public String button;
    public String desc;
    public ADExtra extra;
    public int height;
    public String movie;
    public String pic;
    public String restype;
    public String title;
    public String url;
    public int width;

    public SelfItem(String str, JSONObject jSONObject) {
        this(jSONObject);
        this.postId = str;
    }

    public SelfItem(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    public DataSourceType getDataSourceType() {
        return DataSourceType.SELF;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getHeight() {
        return this.height;
    }

    public String getMovie() {
        return this.movie;
    }

    public String getPic() {
        return this.pic;
    }

    public String getResType() {
        return this.restype;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public int getWidth() {
        return this.width;
    }

    public void jsonToObject(JSONObject jSONObject) {
        boolean z = true;
        this.target = getString("target", jSONObject);
        this.downwarn = getInt("downwarn", jSONObject);
        this.restype = getString("restype", jSONObject);
        this.url = getString("url", jSONObject);
        this.pic = getString("pic", jSONObject);
        this.desc = getString(SocialConstants.PARAM_APP_DESC, jSONObject);
        this.title = getString("title", jSONObject);
        this.movie = getString("movie", jSONObject);
        this.button = getString("button", jSONObject);
        this.restype = getString("restype", jSONObject);
        this.width = getInt(IndexEntry.COLUMN_NAME_WIDTH, jSONObject);
        this.height = getInt(IndexEntry.COLUMN_NAME_HEIGHT, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("extra");
        this.extra = new ADExtra(jSONObject2);
        if (jSONObject2.has("intercept_touch_event")) {
            if (jSONObject2.optInt("intercept_touch_event") != 1) {
                z = false;
            }
            this.isInterceptTouchEvent = z;
        } else if (this.restype != null && this.restype.equals("movie")) {
            this.isInterceptTouchEvent = false;
        }
    }

    public void setButton(String str) {
        this.button = str;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setPic(String str) {
        this.pic = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
