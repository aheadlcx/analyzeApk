package com.sprite.ads.internal.bean.data;

import org.json.JSONObject;

public class ThirdSdkItem extends ThirdItem {
    public String aid;
    public int maxwidth;
    public int movie_interval;
    public int movie_limitofday;
    public int movie_rate;
    public String pid;
    public String pid2;
    public double screen_ratio;
    public SelfItem selfItem;

    public ThirdSdkItem(String str, JSONObject jSONObject) {
        this.postId = str;
        jsonToObject(jSONObject);
    }

    public ThirdSdkItem(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    private void getThirdParams(JSONObject jSONObject) {
        boolean z = true;
        this.aid = getString("sdk.app", jSONObject);
        this.pid = getString("sdk.pos", jSONObject);
        this.pid2 = getString("sdk.pos2", jSONObject);
        this.movie_limitofday = jSONObject.optInt("sdk.movie.limitofday");
        this.movie_interval = jSONObject.optInt("sdk.movie.interval");
        this.movie_rate = jSONObject.optInt("sdk.movie.rate");
        this.screen_ratio = getDouble("screen.ratio", jSONObject);
        this.maxwidth = getInt("maxwidth", jSONObject);
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
        return "sdk";
    }

    public String getTitle() {
        return null;
    }

    public String getUrl() {
        return null;
    }

    public void jsonToObject(JSONObject jSONObject) {
        if (jSONObject.has("extra")) {
            getThirdParams(jSONObject.getJSONObject("extra"));
        }
        this.downwarn = getInt("downwarn", jSONObject);
        this.selfItem = new SelfItem("self." + this.postId, jSONObject);
    }
}
