package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class AdvanceSetting implements Parcelable {
    public static final String ADVANCE_SETTING = "as";
    public static final String CLEAR_NOTIFICATION = "cn";
    public static final Creator<AdvanceSetting> CREATOR = new Creator<AdvanceSetting>() {
        public AdvanceSetting createFromParcel(Parcel parcel) {
            return new AdvanceSetting(parcel);
        }

        public AdvanceSetting[] newArray(int i) {
            return new AdvanceSetting[i];
        }
    };
    public static final String HEAD_UP_NOTIFICATION = "hn";
    public static final String NETWORK_TYPE = "it";
    public static final String NOTIFY_TYPE = "nt";
    public static final String TAG = "advance_setting";
    private boolean clearNotification = true;
    private boolean headUpNotification = true;
    private int netWorkType = 1;
    private NotifyType notifyType;

    public AdvanceSetting(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.netWorkType = parcel.readInt();
        this.notifyType = (NotifyType) parcel.readParcelable(NotifyType.class.getClassLoader());
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.clearNotification = z;
        if (parcel.readByte() == (byte) 0) {
            z2 = false;
        }
        this.headUpNotification = z2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.netWorkType);
        parcel.writeParcelable(this.notifyType, i);
        if (this.clearNotification) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.headUpNotification) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }

    public int getNetWorkType() {
        return this.netWorkType;
    }

    public void setNetWorkType(int i) {
        this.netWorkType = i;
    }

    public NotifyType getNotifyType() {
        return this.notifyType;
    }

    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }

    public boolean isClearNotification() {
        return this.clearNotification;
    }

    public void setClearNotification(boolean z) {
        this.clearNotification = z;
    }

    public boolean isHeadUpNotification() {
        return this.headUpNotification;
    }

    public void setHeadUpNotification(boolean z) {
        this.headUpNotification = z;
    }

    public static AdvanceSetting parse(String str) {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                a.d(TAG, "parse json string error " + e.getMessage());
            }
            return parse(jSONObject);
        }
        jSONObject = null;
        return parse(jSONObject);
    }

    public static AdvanceSetting parse(JSONObject jSONObject) {
        boolean z = true;
        AdvanceSetting advanceSetting = new AdvanceSetting();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(NETWORK_TYPE)) {
                    advanceSetting.setNetWorkType(jSONObject.getInt(NETWORK_TYPE));
                }
                if (!jSONObject.isNull("nt")) {
                    advanceSetting.setNotifyType(NotifyType.parse(jSONObject.getJSONObject("nt")));
                }
                if (!jSONObject.isNull(CLEAR_NOTIFICATION)) {
                    advanceSetting.setClearNotification(jSONObject.getInt(CLEAR_NOTIFICATION) != 0);
                }
                if (!jSONObject.isNull(HEAD_UP_NOTIFICATION)) {
                    if (jSONObject.getInt(HEAD_UP_NOTIFICATION) == 0) {
                        z = false;
                    }
                    advanceSetting.setHeadUpNotification(z);
                }
            } catch (JSONException e) {
                a.d(TAG, "parse json obj error " + e.getMessage());
            }
        } else {
            a.d(TAG, "no such tag advance_setting");
        }
        return advanceSetting;
    }

    public String toString() {
        return "AdvanceSetting{netWorkType=" + this.netWorkType + ", notifyType=" + this.notifyType + ", clearNotification=" + this.clearNotification + ", headUpNotification=" + this.headUpNotification + '}';
    }
}
