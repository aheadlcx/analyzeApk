package com.meizu.cloud.pushsdk.notification;

import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MPushMessage implements Serializable {
    private static final String TAG = "MPushMessage";
    private String clickType;
    private String content;
    private Map<String, String> extra = new HashMap();
    private String isDiscard;
    private String notifyType;
    private String packageName;
    private Map<String, String> params = new HashMap();
    private String pushType;
    private String taskId;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getNotifyType() {
        return this.notifyType;
    }

    public void setNotifyType(String str) {
        this.notifyType = str;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public void setExtra(Map<String, String> map) {
        this.extra = map;
    }

    public String getClickType() {
        return this.clickType;
    }

    public void setClickType(String str) {
        this.clickType = str;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public String getPushType() {
        return this.pushType;
    }

    public void setPushType(String str) {
        this.pushType = str;
    }

    public String getIsDiscard() {
        return this.isDiscard;
    }

    public void setIsDiscard(String str) {
        this.isDiscard = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    public void setParams(Map<String, String> map) {
        this.params = map;
    }

    public String toString() {
        return "MPushMessage{taskId='" + this.taskId + '\'' + ", pushType='" + this.pushType + '\'' + ", packageName='" + this.packageName + '\'' + ", title='" + this.title + '\'' + ", content='" + this.content + '\'' + ", notifyType='" + this.notifyType + '\'' + ", clickType='" + this.clickType + '\'' + ", isDiscard='" + this.isDiscard + '\'' + ", extra=" + this.extra + ", params=" + this.params + '}';
    }

    public static MPushMessage parsePushMessage(String str, String str2, String str3, String str4) {
        MPushMessage mPushMessage = new MPushMessage();
        try {
            mPushMessage.setTaskId(str4);
            mPushMessage.setPushType(str);
            mPushMessage.setPackageName(str3);
            JSONObject jSONObject = new JSONObject(str2).getJSONObject("data");
            if (!jSONObject.isNull("content")) {
                mPushMessage.setContent(jSONObject.getString("content"));
            }
            if (!jSONObject.isNull(PushConstants.IS_DISCARD)) {
                mPushMessage.setIsDiscard(jSONObject.getString(PushConstants.IS_DISCARD));
            }
            if (!jSONObject.isNull("title")) {
                mPushMessage.setTitle(jSONObject.getString("title"));
            }
            if (!jSONObject.isNull(PushConstants.CLICK_TYPE)) {
                mPushMessage.setClickType(jSONObject.getString(PushConstants.CLICK_TYPE));
            }
            if (!jSONObject.isNull(PushConstants.EXTRA)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(PushConstants.EXTRA);
                if (jSONObject2 != null) {
                    if (!jSONObject2.isNull(PushConstants.PARAMS)) {
                        try {
                            jSONObject = jSONObject2.getJSONObject(PushConstants.PARAMS);
                            if (jSONObject != null) {
                                mPushMessage.setParams(getParamsMap(jSONObject));
                            }
                            jSONObject2.remove(PushConstants.PARAMS);
                        } catch (JSONException e) {
                            a.a(TAG, "parameter parse error message " + e.getMessage());
                            if (null != null) {
                                mPushMessage.setParams(getParamsMap(null));
                            }
                            jSONObject2.remove(PushConstants.PARAMS);
                        } catch (Throwable th) {
                            if (null != null) {
                                mPushMessage.setParams(getParamsMap(null));
                            }
                            jSONObject2.remove(PushConstants.PARAMS);
                        }
                    }
                    mPushMessage.setExtra(getParamsMap(jSONObject2));
                }
            }
        } catch (JSONException e2) {
            a.a(TAG, "parse push message error " + e2.getMessage());
        }
        a.a(TAG, " parsePushMessage " + mPushMessage);
        return mPushMessage;
    }

    private static Map<String, String> getParamsMap(JSONObject jSONObject) {
        Map<String, String> hashMap = new HashMap();
        try {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, jSONObject.getString(str));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
