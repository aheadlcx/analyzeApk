package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class AppIconSetting implements Parcelable {
    public static final String APP_ICON_SETTING = "is";
    public static final Creator<AppIconSetting> CREATOR = new Creator<AppIconSetting>() {
        public AppIconSetting createFromParcel(Parcel parcel) {
            return new AppIconSetting(parcel);
        }

        public AppIconSetting[] newArray(int i) {
            return new AppIconSetting[i];
        }
    };
    public static final String DEFAULT_LARGE_ICON = "di";
    public static final String LARGE_ICON_URL = "li";
    public static final String TAG = "app_icon_setting";
    private boolean defaultLargeIcon = true;
    private String largeIconUrl;

    public AppIconSetting(Parcel parcel) {
        boolean z = true;
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.defaultLargeIcon = z;
        this.largeIconUrl = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.defaultLargeIcon ? 1 : 0));
        parcel.writeString(this.largeIconUrl);
    }

    public String getLargeIconUrl() {
        return this.largeIconUrl;
    }

    public void setLargeIconUrl(String str) {
        this.largeIconUrl = str;
    }

    public boolean isDefaultLargeIcon() {
        return this.defaultLargeIcon;
    }

    public void setDefaultLargeIcon(boolean z) {
        this.defaultLargeIcon = z;
    }

    public static AppIconSetting parse(String str) {
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

    public static AppIconSetting parse(JSONObject jSONObject) {
        AppIconSetting appIconSetting = new AppIconSetting();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull("di")) {
                    appIconSetting.setDefaultLargeIcon(jSONObject.getInt("di") != 0);
                }
                if (!jSONObject.isNull(LARGE_ICON_URL)) {
                    appIconSetting.setLargeIconUrl(jSONObject.getString(LARGE_ICON_URL));
                }
            } catch (JSONException e) {
                a.d(TAG, "parse json obj error " + e.getMessage());
            }
        } else {
            a.d(TAG, "no such tag app_icon_setting");
        }
        return appIconSetting;
    }

    public String toString() {
        return "AppIconSetting{defaultLargeIcon=" + this.defaultLargeIcon + ", largeIconUrl='" + this.largeIconUrl + '\'' + '}';
    }
}
