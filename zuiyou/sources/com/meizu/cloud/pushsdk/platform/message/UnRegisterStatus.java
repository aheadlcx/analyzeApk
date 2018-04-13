package com.meizu.cloud.pushsdk.platform.message;

import com.iflytek.cloud.SpeechUtility;
import org.json.JSONException;
import org.json.JSONObject;

public class UnRegisterStatus extends BasicPushStatus {
    private boolean isUnRegisterSuccess;

    public UnRegisterStatus(String str) {
        super(str);
    }

    public void parseValueData(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.isNull(SpeechUtility.TAG_RESOURCE_RESULT)) {
            setIsUnRegisterSuccess(jSONObject.getBoolean(SpeechUtility.TAG_RESOURCE_RESULT));
        }
    }

    public boolean isUnRegisterSuccess() {
        return this.isUnRegisterSuccess;
    }

    public void setIsUnRegisterSuccess(boolean z) {
        this.isUnRegisterSuccess = z;
    }

    public String toString() {
        return super.toString() + " UnRegisterStatus{isUnRegisterSuccess=" + this.isUnRegisterSuccess + '}';
    }
}
