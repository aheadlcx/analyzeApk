package com.sprite.ads.internal.bean.data;

import cn.v6.sixrooms.ui.phone.EventActivity;
import org.json.JSONObject;

public class LinkActiveItem extends ThirdItem {
    public String action;
    public String get_match_root;
    public String get_url;
    public String record_status_url;
    public String request_post_param;
    public double screen_ratio;
    public SelfItem selfItem;

    public LinkActiveItem(String str, JSONObject jSONObject) {
        this.postId = str;
        jsonToObject(jSONObject);
    }

    private void getThirdParams(JSONObject jSONObject) {
        boolean z = true;
        JSONObject jSONObject2 = jSONObject.getJSONObject("rule_extra");
        JSONObject jSONObject3 = jSONObject2.getJSONObject(EventActivity.GOV_REPORT_EVENT);
        JSONObject jSONObject4 = jSONObject2.getJSONObject("request");
        this.screen_ratio = getDouble("screen.ratio", jSONObject);
        this.refreshTime = getInt("refresh_interval", jSONObject);
        if (jSONObject.has("is_load_next_ad")) {
            if (jSONObject.getInt("is_load_next_ad") != 1) {
                z = false;
            }
            this.isLoadNextAd = z;
        }
        this.get_match_root = getString("node", jSONObject2);
        this.request_post_param = getString("post_data", jSONObject4);
        this.action = getString("request_type", jSONObject4);
        this.get_url = getString("request_url", jSONObject4);
        this.record_status_url = getString("record_status_url", jSONObject3);
    }

    public SelfItem getDefAdItem() {
        return this.selfItem;
    }

    public String getDesc() {
        return null;
    }

    public String getMovie() {
        return null;
    }

    public String getPic() {
        return null;
    }

    public String getResType() {
        return null;
    }

    public String getTitle() {
        return null;
    }

    public String getUrl() {
        return null;
    }

    public void jsonToObject(JSONObject jSONObject) {
        this.downwarn = getInt("downwarn", jSONObject);
        this.target = getString("target", jSONObject);
        getThirdParams(jSONObject.getJSONObject("extra"));
        this.selfItem = new SelfItem("self." + this.postId, jSONObject);
    }
}
