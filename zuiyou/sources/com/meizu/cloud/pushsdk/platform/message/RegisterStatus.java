package com.meizu.cloud.pushsdk.platform.message;

import com.meizu.cloud.pushsdk.constants.PushConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterStatus extends BasicPushStatus {
    private int expireTime;
    private String pushId;

    public RegisterStatus(String str) {
        super(str);
    }

    public void parseValueData(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.isNull(PushConstants.KEY_PUSH_ID)) {
            setPushId(jSONObject.getString(PushConstants.KEY_PUSH_ID));
        }
        if (!jSONObject.isNull("expireTime")) {
            setExpireTime(jSONObject.getInt("expireTime"));
        }
    }

    public String getPushId() {
        return this.pushId;
    }

    public void setPushId(String str) {
        this.pushId = str;
    }

    public int getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(int i) {
        this.expireTime = i;
    }

    public String toString() {
        return super.toString() + "pushId='" + this.pushId + '\'' + ", Become invalid after " + this.expireTime + " seconds " + '}';
    }
}
