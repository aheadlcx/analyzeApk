package com.meizu.cloud.pushsdk.handler;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.notification.MPushMessage;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import com.meizu.cloud.pushsdk.notification.model.NotificationStyle;
import com.meizu.cloud.pushsdk.notification.model.TimeDisplaySetting;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageV3 implements Parcelable {
    public static final Creator<MessageV3> CREATOR = new Creator<MessageV3>() {
        public MessageV3 createFromParcel(Parcel parcel) {
            return new MessageV3(parcel);
        }

        public MessageV3[] newArray(int i) {
            return new MessageV3[i];
        }
    };
    private static final String TAG = "Message_V3";
    private String activity;
    private int clickType;
    private String content;
    private String deviceId;
    private boolean isDiscard;
    private AdvanceSetting mAdvanceSetting;
    private AppIconSetting mAppIconSetting;
    private NotificationStyle mNotificationStyle;
    private TimeDisplaySetting mTimeDisplaySetting;
    private String notificationMessage;
    private String packageName;
    private Map<String, String> paramsMap = new HashMap();
    private String pushTimestamp;
    private String seqId;
    private String taskId;
    private String throughMessage;
    private String title;
    private String uploadDataPackageName;
    private String uriPackageName;
    private String webUrl;

    public enum CLICK_TYPE_DEFINE {
        CLICK_TYPE_LAUNCHER_ACTIVITY,
        CLICK_TYPE_ACTIVITY,
        CLICK_TYPE_WEB
    }

    public MessageV3(Parcel parcel) {
        this.taskId = parcel.readString();
        this.seqId = parcel.readString();
        this.deviceId = parcel.readString();
        this.title = parcel.readString();
        this.content = parcel.readString();
        this.packageName = parcel.readString();
        this.clickType = parcel.readInt();
        this.isDiscard = parcel.readByte() != (byte) 0;
        this.activity = parcel.readString();
        this.webUrl = parcel.readString();
        this.uriPackageName = parcel.readString();
        this.uploadDataPackageName = parcel.readString();
        this.pushTimestamp = parcel.readString();
        this.paramsMap = parcel.readHashMap(getClass().getClassLoader());
        this.throughMessage = parcel.readString();
        this.notificationMessage = parcel.readString();
        this.mAdvanceSetting = (AdvanceSetting) parcel.readParcelable(AdvanceSetting.class.getClassLoader());
        this.mAppIconSetting = (AppIconSetting) parcel.readParcelable(AppIconSetting.class.getClassLoader());
        this.mNotificationStyle = (NotificationStyle) parcel.readParcelable(NotificationStyle.class.getClassLoader());
        this.mTimeDisplaySetting = (TimeDisplaySetting) parcel.readParcelable(TimeDisplaySetting.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.taskId);
        parcel.writeString(this.seqId);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.title);
        parcel.writeString(this.content);
        parcel.writeString(this.packageName);
        parcel.writeInt(this.clickType);
        parcel.writeByte((byte) (this.isDiscard ? 1 : 0));
        parcel.writeString(this.activity);
        parcel.writeString(this.webUrl);
        parcel.writeString(this.uriPackageName);
        parcel.writeString(this.uploadDataPackageName);
        parcel.writeString(this.pushTimestamp);
        parcel.writeMap(this.paramsMap);
        parcel.writeString(this.throughMessage);
        parcel.writeString(this.notificationMessage);
        parcel.writeParcelable(this.mAdvanceSetting, i);
        parcel.writeParcelable(this.mAppIconSetting, i);
        parcel.writeParcelable(this.mNotificationStyle, i);
        parcel.writeParcelable(this.mTimeDisplaySetting, i);
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public AdvanceSetting getmAdvanceSetting() {
        return this.mAdvanceSetting;
    }

    public void setmAdvanceSetting(AdvanceSetting advanceSetting) {
        this.mAdvanceSetting = advanceSetting;
    }

    public AppIconSetting getmAppIconSetting() {
        return this.mAppIconSetting;
    }

    public void setmAppIconSetting(AppIconSetting appIconSetting) {
        this.mAppIconSetting = appIconSetting;
    }

    public NotificationStyle getmNotificationStyle() {
        return this.mNotificationStyle;
    }

    public void setmNotificationStyle(NotificationStyle notificationStyle) {
        this.mNotificationStyle = notificationStyle;
    }

    public TimeDisplaySetting getmTimeDisplaySetting() {
        return this.mTimeDisplaySetting;
    }

    public void setmTimeDisplaySetting(TimeDisplaySetting timeDisplaySetting) {
        this.mTimeDisplaySetting = timeDisplaySetting;
    }

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

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public int getClickType() {
        return this.clickType;
    }

    public void setClickType(int i) {
        this.clickType = i;
    }

    public boolean isDiscard() {
        return this.isDiscard;
    }

    public void setIsDiscard(boolean z) {
        this.isDiscard = z;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String str) {
        this.activity = str;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public void setWebUrl(String str) {
        this.webUrl = str;
    }

    public String getUriPackageName() {
        return this.uriPackageName;
    }

    public void setUriPackageName(String str) {
        this.uriPackageName = str;
    }

    public String getPushTimestamp() {
        return this.pushTimestamp;
    }

    public void setPushTimestamp(String str) {
        this.pushTimestamp = str;
    }

    public Map<String, String> getParamsMap() {
        return this.paramsMap;
    }

    public void setParamsMap(Map<String, String> map) {
        this.paramsMap = map;
    }

    public String getThroughMessage() {
        return this.throughMessage;
    }

    public void setThroughMessage(String str) {
        this.throughMessage = str;
    }

    public String getNotificationMessage() {
        return this.notificationMessage;
    }

    public void setNotificationMessage(String str) {
        this.notificationMessage = str;
    }

    public String getSeqId() {
        return this.seqId;
    }

    public void setSeqId(String str) {
        this.seqId = str;
    }

    public String getUploadDataPackageName() {
        return this.uploadDataPackageName;
    }

    public void setUploadDataPackageName(String str) {
        this.uploadDataPackageName = str;
    }

    public static MessageV3 parse(String str, String str2, String str3, MPushMessage mPushMessage) {
        a.d(TAG, "V2 message " + mPushMessage);
        MessageV3 messageV3 = new MessageV3();
        messageV3.setPackageName(str);
        messageV3.setUploadDataPackageName(str);
        messageV3.setDeviceId(str2);
        messageV3.setTaskId(str3);
        messageV3.setTitle(mPushMessage.getTitle());
        messageV3.setContent(mPushMessage.getContent());
        messageV3.setIsDiscard("true".equals(mPushMessage.getIsDiscard()));
        messageV3.setClickType(Integer.valueOf(mPushMessage.getClickType()).intValue());
        for (Entry entry : mPushMessage.getExtra().entrySet()) {
            String str4 = (String) entry.getKey();
            String str5 = (String) entry.getValue();
            if ("activity".equals(str4)) {
                messageV3.setActivity(str5);
            }
            if ("url".equals(str4)) {
                messageV3.setWebUrl(str5);
            }
            if (PushConstants.URI_PACKAGE_NAME.equals(str4)) {
                messageV3.setUriPackageName(str5);
            }
            if (NotificationStyle.NOTIFICATION_STYLE.equals(str4)) {
                messageV3.setmNotificationStyle(NotificationStyle.parse(str5));
            }
            if (AdvanceSetting.ADVANCE_SETTING.equals(str4)) {
                messageV3.setmAdvanceSetting(AdvanceSetting.parse(str5));
            }
            if ("is".equals(str4)) {
                messageV3.setmAppIconSetting(AppIconSetting.parse(str5));
            }
            if ("ts".equals(str4)) {
                messageV3.setmTimeDisplaySetting(TimeDisplaySetting.parse(str5));
            }
        }
        messageV3.setParamsMap(mPushMessage.getParams());
        a.a(TAG, "parase V2 message to V3 message " + messageV3);
        return messageV3;
    }

    public static MessageV3 parse(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        MessageV3 parse = parse(str, str4, str5, str6, str7);
        parse.setUploadDataPackageName(str2);
        parse.setPushTimestamp(str3);
        return parse;
    }

    public static MessageV3 parse(String str, String str2, String str3, String str4, String str5) {
        MessageV3 parse = parse(str, str2, str3, str5);
        parse.setSeqId(str4);
        return parse;
    }

    public static MessageV3 parse(String str, String str2, String str3, String str4) {
        MessageV3 messageV3 = new MessageV3();
        messageV3.setNotificationMessage(str4);
        messageV3.setTaskId(str3);
        messageV3.setDeviceId(str2);
        messageV3.setPackageName(str);
        try {
            JSONObject jSONObject = new JSONObject(str4).getJSONObject("data");
            if (!jSONObject.isNull("title")) {
                messageV3.setTitle(jSONObject.getString("title"));
            }
            if (!jSONObject.isNull("content")) {
                messageV3.setContent(jSONObject.getString("content"));
            }
            if (!jSONObject.isNull(PushConstants.IS_DISCARD)) {
                messageV3.setIsDiscard(jSONObject.getBoolean(PushConstants.IS_DISCARD));
            }
            if (!jSONObject.isNull(PushConstants.CLICK_TYPE)) {
                messageV3.setClickType(jSONObject.getInt(PushConstants.CLICK_TYPE));
            }
            if (!jSONObject.isNull(PushConstants.EXTRA)) {
                jSONObject = jSONObject.getJSONObject(PushConstants.EXTRA);
                if (!jSONObject.isNull(NotificationStyle.NOTIFICATION_STYLE)) {
                    messageV3.setmNotificationStyle(NotificationStyle.parse(jSONObject.getJSONObject(NotificationStyle.NOTIFICATION_STYLE)));
                }
                if (!jSONObject.isNull("is")) {
                    messageV3.setmAppIconSetting(AppIconSetting.parse(jSONObject.getJSONObject("is")));
                }
                if (!jSONObject.isNull(AdvanceSetting.ADVANCE_SETTING)) {
                    messageV3.setmAdvanceSetting(AdvanceSetting.parse(jSONObject.getJSONObject(AdvanceSetting.ADVANCE_SETTING)));
                }
                if (!jSONObject.isNull("ts")) {
                    messageV3.setmTimeDisplaySetting(TimeDisplaySetting.parse(jSONObject.getJSONObject("ts")));
                }
                if (!jSONObject.isNull("activity")) {
                    messageV3.setActivity(jSONObject.getString("activity"));
                }
                if (!jSONObject.isNull("url")) {
                    messageV3.setWebUrl(jSONObject.getString("url"));
                }
                if (!jSONObject.isNull("task_id") && TextUtils.isEmpty(str3)) {
                    a.d(TAG, "Flyme 4 notification message by through message or taskId is null");
                    messageV3.setTaskId(jSONObject.getString("task_id"));
                }
                if (!jSONObject.isNull(PushConstants.URI_PACKAGE_NAME)) {
                    messageV3.setUriPackageName(jSONObject.getString(PushConstants.URI_PACKAGE_NAME));
                }
                if (!jSONObject.isNull(PushConstants.PARAMS)) {
                    messageV3.setParamsMap(getParamsMap(jSONObject.getJSONObject(PushConstants.PARAMS)));
                }
            }
        } catch (JSONException e) {
            a.d(TAG, "parse message error " + e.getMessage());
        }
        return messageV3;
    }

    public static Map<String, String> getParamsMap(JSONObject jSONObject) {
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

    public String toString() {
        return "MessageV3{taskId='" + this.taskId + '\'' + ", seqId='" + this.seqId + '\'' + ", deviceId='" + this.deviceId + '\'' + ", title='" + this.title + '\'' + ", content='" + this.content + '\'' + ", packageName='" + this.packageName + '\'' + ", clickType=" + this.clickType + ", isDiscard=" + this.isDiscard + ", activity='" + this.activity + '\'' + ", webUrl='" + this.webUrl + '\'' + ", uriPackageName='" + this.uriPackageName + '\'' + ", pushTimestamp='" + this.pushTimestamp + '\'' + ", uploadDataPackageName='" + this.uploadDataPackageName + '\'' + ", paramsMap=" + this.paramsMap + ", throughMessage='" + this.throughMessage + '\'' + ", notificationMessage='" + this.notificationMessage + '\'' + ", mAdvanceSetting=" + this.mAdvanceSetting + ", mAppIconSetting=" + this.mAppIconSetting + ", mNotificationStyle=" + this.mNotificationStyle + ", mTimeDisplaySetting=" + this.mTimeDisplaySetting + '}';
    }
}
