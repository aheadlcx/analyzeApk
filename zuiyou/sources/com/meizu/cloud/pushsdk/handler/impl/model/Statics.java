package com.meizu.cloud.pushsdk.handler.impl.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class Statics implements Parcelable {
    public static final Creator<Statics> CREATOR = new Creator<Statics>() {
        public Statics createFromParcel(Parcel parcel) {
            return new Statics(parcel);
        }

        public Statics[] newArray(int i) {
            return new Statics[i];
        }
    };
    public static final String PUSH_EXTRA = "pushExtra";
    public static final String TAG = "statics";
    public static final String TASK_ID = "taskId";
    public static final String TIME = "time";
    private String deviceId;
    private boolean pushExtra = false;
    private String seqId;
    private String taskId;
    private String time;

    protected Statics(Parcel parcel) {
        boolean z = false;
        this.taskId = parcel.readString();
        this.time = parcel.readString();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        }
        this.pushExtra = z;
        this.deviceId = parcel.readString();
        this.seqId = parcel.readString();
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public boolean getPushExtra() {
        return this.pushExtra;
    }

    public void setPushExtra(boolean z) {
        this.pushExtra = z;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getSeqId() {
        return this.seqId;
    }

    public void setSeqId(String str) {
        this.seqId = str;
    }

    public static Statics parse(JSONObject jSONObject) {
        Statics statics = new Statics();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(TASK_ID)) {
                    statics.setTaskId(jSONObject.getString(TASK_ID));
                }
                if (!jSONObject.isNull(TIME)) {
                    statics.setTime(jSONObject.getString(TIME));
                }
                if (!jSONObject.isNull(PUSH_EXTRA)) {
                    statics.setPushExtra(jSONObject.getInt(PUSH_EXTRA) != 0);
                }
            } catch (JSONException e) {
                a.d(TAG, " parse statics message error " + e.getMessage());
            }
        } else {
            a.d(TAG, "no control statics can parse ");
        }
        return statics;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.taskId);
        parcel.writeString(this.time);
        parcel.writeByte((byte) (this.pushExtra ? 1 : 0));
        parcel.writeString(this.deviceId);
        parcel.writeString(this.seqId);
    }

    public String toString() {
        return "Statics{taskId='" + this.taskId + '\'' + ", time='" + this.time + '\'' + ", pushExtra=" + this.pushExtra + ", deviceId='" + this.deviceId + '\'' + ", seqId='" + this.seqId + '\'' + '}';
    }
}
