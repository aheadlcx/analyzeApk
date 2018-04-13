package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class TimeDisplaySetting implements Parcelable {
    public static final Creator<TimeDisplaySetting> CREATOR = new Creator<TimeDisplaySetting>() {
        public TimeDisplaySetting createFromParcel(Parcel parcel) {
            return new TimeDisplaySetting(parcel);
        }

        public TimeDisplaySetting[] newArray(int i) {
            return new TimeDisplaySetting[i];
        }
    };
    public static final String END_SHOW_TIME = "et";
    public static final String START_SHOW_TIME = "st";
    public static final String TAG = "time_display_setting";
    public static final String TIME_DISPLAY = "td";
    public static final String TIME_DISPLAY_SETTING = "ts";
    private String endShowTime;
    private boolean isTimeDisplay;
    private String startShowTime;

    public TimeDisplaySetting(Parcel parcel) {
        this.isTimeDisplay = parcel.readByte() != (byte) 0;
        this.startShowTime = parcel.readString();
        this.endShowTime = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.isTimeDisplay ? 1 : 0));
        parcel.writeString(this.startShowTime);
        parcel.writeString(this.endShowTime);
    }

    public boolean isTimeDisplay() {
        return this.isTimeDisplay;
    }

    public void setIsTimeDisplay(boolean z) {
        this.isTimeDisplay = z;
    }

    public String getStartShowTime() {
        return this.startShowTime;
    }

    public void setStartShowTime(String str) {
        this.startShowTime = str;
    }

    public String getEndShowTime() {
        return this.endShowTime;
    }

    public void setEndShowTime(String str) {
        this.endShowTime = str;
    }

    public static TimeDisplaySetting parse(String str) {
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

    public static TimeDisplaySetting parse(JSONObject jSONObject) {
        TimeDisplaySetting timeDisplaySetting = new TimeDisplaySetting();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(TIME_DISPLAY)) {
                    timeDisplaySetting.setIsTimeDisplay(jSONObject.getInt(TIME_DISPLAY) != 0);
                }
                if (!jSONObject.isNull(START_SHOW_TIME)) {
                    timeDisplaySetting.setStartShowTime(jSONObject.getString(START_SHOW_TIME));
                }
                if (!jSONObject.isNull("et")) {
                    timeDisplaySetting.setEndShowTime(jSONObject.getString("et"));
                }
            } catch (JSONException e) {
                a.d(TAG, "parse json obj error " + e.getMessage());
            }
        } else {
            a.d(TAG, "no such tag time_display_setting");
        }
        return timeDisplaySetting;
    }

    public String toString() {
        return "TimeDisplaySetting{isTimeDisplay=" + this.isTimeDisplay + ", startShowTime='" + this.startShowTime + '\'' + ", endShowTime='" + this.endShowTime + '\'' + '}';
    }
}
