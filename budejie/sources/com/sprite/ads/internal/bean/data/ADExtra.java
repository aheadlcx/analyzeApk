package com.sprite.ads.internal.bean.data;

import com.sprite.ads.internal.bean.JsonInterface;
import java.io.Serializable;
import org.json.JSONObject;

public class ADExtra implements JsonInterface, Serializable {
    public String score;
    public double screen_ratio;
    public String sdkBtnCancel;
    public String third_report_url;

    public ADExtra(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    public void jsonToObject(JSONObject jSONObject) {
        if (jSONObject.has("score")) {
            this.score = jSONObject.getString("score");
        }
        if (jSONObject.has("sdk.btn.cancel")) {
            this.sdkBtnCancel = jSONObject.getString("sdk.btn.cancel");
        }
        if (jSONObject.has("third_report_url")) {
            this.third_report_url = jSONObject.getString("third_report_url");
        }
        if (jSONObject.has("screen.ratio")) {
            this.screen_ratio = jSONObject.getDouble("screen.ratio");
        }
    }
}
