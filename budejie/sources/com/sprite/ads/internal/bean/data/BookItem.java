package com.sprite.ads.internal.bean.data;

import org.json.JSONObject;

public class BookItem extends ThirdItem {
    public int ad_id;
    public int isdefault;
    private String url;

    public BookItem(String str, JSONObject jSONObject) {
        this.postId = str;
        jsonToObject(jSONObject);
    }

    public BookItem(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    private void getThirdParams(JSONObject jSONObject) {
        boolean z = true;
        this.isdefault = getInt("isdefault", jSONObject);
        this.refreshTime = getInt("refresh_interval", jSONObject);
        if (jSONObject.has("is_load_next_ad")) {
            if (jSONObject.getInt("is_load_next_ad") != 1) {
                z = false;
            }
            this.isLoadNextAd = z;
        }
    }

    public SelfItem getDefAdItem() {
        return null;
    }

    public String getDesc() {
        return null;
    }

    public boolean getInterceptTouchEvent() {
        return false;
    }

    public String getMovie() {
        return null;
    }

    public String getPic() {
        return null;
    }

    public String getResType() {
        return "book";
    }

    public String getTitle() {
        return null;
    }

    public String getUrl() {
        return this.url;
    }

    public void jsonToObject(JSONObject jSONObject) {
        this.ad_id = getInt("ad_id", jSONObject);
        getThirdParams(jSONObject.getJSONObject("extra"));
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
