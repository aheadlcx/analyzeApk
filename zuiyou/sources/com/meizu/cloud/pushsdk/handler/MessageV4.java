package com.meizu.cloud.pushsdk.handler;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.notification.model.ActVideoSetting;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageV4 extends MessageV3 {
    public static final Creator<MessageV3> CREATOR = new Creator<MessageV3>() {
        public MessageV4 createFromParcel(Parcel parcel) {
            return new MessageV4(parcel);
        }

        public MessageV4[] newArray(int i) {
            return new MessageV4[i];
        }
    };
    private static final String TAG = "MessageV4";
    private ActVideoSetting actVideoSetting;

    public MessageV4(Parcel parcel) {
        super(parcel);
        this.actVideoSetting = (ActVideoSetting) parcel.readParcelable(ActVideoSetting.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.actVideoSetting, i);
    }

    public ActVideoSetting getActVideoSetting() {
        return this.actVideoSetting;
    }

    public void setActVideoSetting(ActVideoSetting actVideoSetting) {
        this.actVideoSetting = actVideoSetting;
    }

    public String toString() {
        return "MessageV4{actVideoSetting=" + this.actVideoSetting + '}' + super.toString();
    }

    public static MessageV4 parse(MessageV3 messageV3) {
        MessageV4 messageV4 = new MessageV4();
        if (!TextUtils.isEmpty(messageV3.getNotificationMessage())) {
            try {
                JSONObject jSONObject = new JSONObject(messageV3.getNotificationMessage()).getJSONObject("data");
                if (!jSONObject.isNull(PushConstants.EXTRA)) {
                    jSONObject = jSONObject.getJSONObject(PushConstants.EXTRA);
                    if (!jSONObject.isNull(ActVideoSetting.ACT_VIDEO_SETTING)) {
                        messageV4.setActVideoSetting(ActVideoSetting.parse(jSONObject.getJSONObject(ActVideoSetting.ACT_VIDEO_SETTING)));
                    }
                }
            } catch (JSONException e) {
                a.d(TAG, "parse messageV4 error " + e.getMessage());
            }
        }
        a.a(TAG, "MessageV4 " + messageV4);
        return messageV4;
    }
}
