package com.sprite.ads.internal.bean.data;

import org.json.JSONObject;

public class SixRoomItem extends ThirdItem {
    private int event_ype;
    public int item_count;
    public int item_limit;
    public double screen_ratio;
    public SelfItem selfItem;
    private int uid;
    private String url;

    public SixRoomItem(String str, JSONObject jSONObject) {
        this.postId = str;
        jsonToObject(jSONObject);
    }

    public SixRoomItem(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    private void getThirdParams(JSONObject jSONObject) {
        boolean z = true;
        this.screen_ratio = getDouble("screen_ratio", jSONObject);
        this.item_limit = getInt("item_limit", jSONObject);
        if (this.item_limit == 0) {
            this.item_limit = 20;
        }
        this.item_count = getInt("item_count", jSONObject);
        this.refreshTime = getInt("refresh_interval", jSONObject);
        if (jSONObject.has("is_load_next_ad")) {
            if (jSONObject.getInt("is_load_next_ad") != 1) {
                z = false;
            }
            this.isLoadNextAd = z;
        }
    }

    public SelfItem getDefAdItem() {
        return this.selfItem;
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
        return "live";
    }

    public String getTitle() {
        return null;
    }

    public int getUid() {
        return this.uid;
    }

    public String getUrl() {
        return this.url;
    }

    public void jsonToObject(JSONObject jSONObject) {
        if (jSONObject.has("extra")) {
            getThirdParams(jSONObject.getJSONObject("extra"));
        }
        this.downwarn = getInt("downwarn", jSONObject);
        this.selfItem = null;
    }

    public void setEventType(int i) {
        this.event_ype = i;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
