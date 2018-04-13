package com.sprite.ads.internal.bean.data;

import org.json.JSONObject;

public class ThirdApiItem extends ThirdItem {
    public String action;
    public String get_match_addr;
    public String get_match_desc;
    public String get_match_img;
    public String get_match_root;
    public String get_match_title;
    public String get_match_type;
    public String get_url;
    public String report_click;
    public String report_click_addr;
    public String report_click_clickid;
    public String report_click_param;
    public String report_param;
    public String report_url;
    public double screen_ratio;
    public String sdk_app;
    public String sdk_pos;
    public SelfItem selfItem;
    public String status_get;
    public String status_report_click;
    public String status_report_show;

    public ThirdApiItem(String str, JSONObject jSONObject) {
        this.postId = str;
        jsonToObject(jSONObject);
    }

    public ThirdApiItem(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    private void getThirdParams(JSONObject jSONObject) {
        boolean z = true;
        this.sdk_app = getString("sdk.app", jSONObject);
        this.sdk_pos = getString("sdk.pos", jSONObject);
        this.get_url = getString("get.url", jSONObject);
        this.get_match_img = getString("get.match.img", jSONObject);
        this.get_match_title = getString("get.match.title", jSONObject);
        this.get_match_desc = getString("get.match.desc", jSONObject);
        this.get_match_type = getString("get.match.type", jSONObject);
        this.get_match_root = getString("get.match.root", jSONObject);
        this.get_match_addr = getString("get.match.addr", jSONObject);
        this.report_url = getString("report.url", jSONObject);
        this.report_param = getString("report.param", jSONObject);
        this.report_click = getString("report.click", jSONObject);
        this.report_click_addr = getString("report.click.addr", jSONObject);
        this.report_click_param = getString("report.click.param", jSONObject);
        this.report_click_clickid = getString("report.click.clickid", jSONObject);
        this.status_get = getString("status.get", jSONObject);
        this.status_report_show = getString("status.report.show", jSONObject);
        this.status_report_click = getString("status.report.click", jSONObject);
        this.screen_ratio = getDouble("screen.ratio", jSONObject);
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

    public String getMovie() {
        return null;
    }

    public String getPic() {
        return null;
    }

    public String getResType() {
        return "api";
    }

    public String getTitle() {
        return null;
    }

    public String getUrl() {
        return null;
    }

    public void jsonToObject(JSONObject jSONObject) {
        this.downwarn = getInt("downwarn", jSONObject);
        this.action = getString("action", jSONObject);
        this.target = getString("target", jSONObject);
        getThirdParams(jSONObject.getJSONObject("extra"));
        this.selfItem = new SelfItem("self." + this.postId, jSONObject);
    }
}
