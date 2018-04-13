package com.sprite.ads.internal.bean.data;

import cn.v6.sixrooms.ui.phone.EventActivity;
import com.sprite.ads.internal.utils.c;
import com.tencent.open.GameAppOperation;
import com.tencent.open.SocialConstants;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONArray;
import org.json.JSONObject;

public class ThirdApiItem2 extends ThirdItem {
    public String action;
    public String action_type_download_value;
    public String action_type_property;
    public String action_type_web_value;
    public String click_through_url;
    public String click_tracking_url;
    public String download_complete_url;
    public String download_installed_url;
    public String download_start_url;
    public String get_match_desc;
    public String get_match_img;
    public String get_match_root;
    public String get_match_title;
    public String get_match_video;
    public String get_url;
    public String report_tracking_url;
    public String request_post_param;
    public String res_type_image_value;
    public String res_type_property;
    public String res_type_video_value;
    public double screen_ratio;
    public SelfItem selfItem;
    public String video_complete_url;
    public String video_mid_point_url;
    public String video_start_url;

    public ThirdApiItem2(String str, JSONObject jSONObject) {
        this.postId = str;
        jsonToObject(jSONObject);
    }

    public ThirdApiItem2(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    private void getThirdParams(JSONObject jSONObject) {
        JSONArray jSONArray;
        int i;
        boolean z = true;
        JSONObject jSONObject2 = jSONObject.getJSONObject("rule_extra");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("fields");
        JSONObject jSONObject4 = jSONObject2.getJSONObject(EventActivity.GOV_REPORT_EVENT);
        JSONObject jSONObject5 = jSONObject2.getJSONObject("request");
        this.screen_ratio = getDouble("screen.ratio", jSONObject);
        this.refreshTime = getInt("refresh_interval", jSONObject);
        if (jSONObject.has("is_load_next_ad")) {
            if (jSONObject.getInt("is_load_next_ad") != 1) {
                z = false;
            }
            this.isLoadNextAd = z;
        }
        this.get_match_root = getString("node", jSONObject2);
        this.get_url = getString("request_url", jSONObject5);
        this.action = getString("request_type", jSONObject5);
        this.request_post_param = getString("post_data", jSONObject5);
        this.get_match_img = getString(GameAppOperation.QQFAV_DATALINE_IMAGEURL, jSONObject3);
        this.get_match_title = getString("title", jSONObject3);
        this.get_match_desc = getString(SocialConstants.PARAM_APP_DESC, jSONObject3);
        this.get_match_video = getString("video_url", jSONObject3);
        if (jSONObject3.has("restype")) {
            jSONArray = jSONObject3.getJSONArray("restype");
            for (i = 0; i < jSONArray.length(); i++) {
                jSONObject5 = jSONArray.getJSONObject(i);
                if (jSONObject5.has(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY)) {
                    jSONObject5 = jSONObject5.getJSONObject(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY);
                    this.res_type_property = jSONObject5.names().getString(0);
                    this.res_type_image_value = jSONObject5.getString(this.res_type_property);
                } else if (jSONObject5.has("video")) {
                    jSONObject5 = jSONObject5.getJSONObject("video");
                    this.res_type_property = jSONObject5.names().getString(0);
                    this.res_type_video_value = jSONObject5.getString(this.res_type_property);
                }
            }
        }
        if (jSONObject3.has("actiontype")) {
            jSONArray = jSONObject3.getJSONArray("actiontype");
            for (i = 0; i < jSONArray.length(); i++) {
                jSONObject3 = jSONArray.getJSONObject(i);
                if (jSONObject3.has("web")) {
                    jSONObject3 = jSONObject3.getJSONObject("web");
                    this.action_type_property = jSONObject3.names().getString(0);
                    this.action_type_web_value = jSONObject3.getString(this.action_type_property);
                } else if (jSONObject3.has("download")) {
                    jSONObject3 = jSONObject3.getJSONObject("download");
                    this.action_type_property = jSONObject3.names().getString(0);
                    this.action_type_download_value = jSONObject3.getString(this.action_type_property);
                }
            }
        }
        this.click_through_url = getString("click_through_url", jSONObject4);
        this.click_tracking_url = getString("click_tracking_url", jSONObject4);
        this.report_tracking_url = getString("report_tracking_url", jSONObject4);
        this.download_start_url = c.a("download.event_start", jSONObject4);
        this.download_complete_url = c.a("download.event_complete", jSONObject4);
        this.download_installed_url = c.a("download.event_installed", jSONObject4);
        this.video_start_url = c.a("video.event_start", jSONObject4);
        this.video_complete_url = c.a("video.event_complete", jSONObject4);
        this.video_mid_point_url = c.a("video.event_mid_point", jSONObject4);
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
        this.target = getString("target", jSONObject);
        getThirdParams(jSONObject.getJSONObject("extra"));
        this.selfItem = new SelfItem("self." + this.postId, jSONObject);
    }
}
