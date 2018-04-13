package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class ActVideoSetting implements Parcelable {
    public static final String ACT_URL = "au";
    public static final String ACT_VIDEO_SETTING = "acts";
    public static final Creator<ActVideoSetting> CREATOR = new Creator<ActVideoSetting>() {
        public ActVideoSetting createFromParcel(Parcel parcel) {
            return new ActVideoSetting(parcel);
        }

        public ActVideoSetting[] newArray(int i) {
            return new ActVideoSetting[i];
        }
    };
    public static final String TAG = "ActVideoSetting";
    public static final String WIFI_DISPLAY = "wd";
    private String actUrl;
    private boolean wifiDisplay;

    protected ActVideoSetting(Parcel parcel) {
        this.wifiDisplay = parcel.readByte() != (byte) 0;
        this.actUrl = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.wifiDisplay ? 1 : 0));
        parcel.writeString(this.actUrl);
    }

    public boolean isWifiDisplay() {
        return this.wifiDisplay;
    }

    public void setWifiDisplay(boolean z) {
        this.wifiDisplay = z;
    }

    public String getActUrl() {
        return this.actUrl;
    }

    public void setActUrl(String str) {
        this.actUrl = str;
    }

    public String toString() {
        return "ActVideoSetting{wifiDisplay=" + this.wifiDisplay + ", actUrl='" + this.actUrl + '\'' + '}';
    }

    public static ActVideoSetting parse(String str) {
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

    public static ActVideoSetting parse(JSONObject jSONObject) {
        ActVideoSetting actVideoSetting = new ActVideoSetting();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(WIFI_DISPLAY)) {
                    actVideoSetting.setWifiDisplay(jSONObject.getInt(WIFI_DISPLAY) != 0);
                }
                if (!jSONObject.isNull(ACT_URL)) {
                    actVideoSetting.setActUrl(jSONObject.getString(ACT_URL));
                }
            } catch (JSONException e) {
                a.d(TAG, "parse json obj error " + e.getMessage());
            }
        } else {
            a.d(TAG, "no such tag ActVideoSetting");
        }
        return actVideoSetting;
    }
}
