package qsbk.app.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.im.IMChatMsgSource;

public class NewFan extends UserInfo {
    public int mShakeTime;
    public int mShakeTimes;
    public IMChatMsgSource mSource;

    public void parseData(JSONObject jSONObject) {
        updateServerJsonNearby(this, jSONObject);
        if (jSONObject.has("come_from")) {
            Object optString = jSONObject.optString("come_from");
            if (!TextUtils.isEmpty(optString)) {
                this.mSource = new IMChatMsgSource();
                try {
                    this.mSource.parseFromJSONObject(new JSONObject(optString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (jSONObject.has("shake_time")) {
            this.mShakeTime = jSONObject.optInt("shake_time");
        }
        if (jSONObject.has("shake_count")) {
            this.mShakeTimes = jSONObject.optInt("shake_count");
        }
    }
}
