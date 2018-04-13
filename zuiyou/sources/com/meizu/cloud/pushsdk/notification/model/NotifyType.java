package com.meizu.cloud.pushsdk.notification.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class NotifyType implements Parcelable {
    public static final Creator<NotifyType> CREATOR = new Creator<NotifyType>() {
        public NotifyType createFromParcel(Parcel parcel) {
            return new NotifyType(parcel);
        }

        public NotifyType[] newArray(int i) {
            return new NotifyType[i];
        }
    };
    public static final String LIGHTS = "l";
    public static final String NOTIFY_TYPE = "nt";
    public static final String SOUND = "s";
    public static final String TAG = "notify_type";
    public static final String VIBRATE = "v";
    boolean lights;
    boolean sound;
    boolean vibrate;

    public NotifyType(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.vibrate = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.lights = z;
        if (parcel.readByte() == (byte) 0) {
            z2 = false;
        }
        this.sound = z2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        if (this.vibrate) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.lights) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.sound) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
    }

    public boolean isVibrate() {
        return this.vibrate;
    }

    public void setVibrate(boolean z) {
        this.vibrate = z;
    }

    public boolean isLights() {
        return this.lights;
    }

    public void setLights(boolean z) {
        this.lights = z;
    }

    public boolean isSound() {
        return this.sound;
    }

    public void setSound(boolean z) {
        this.sound = z;
    }

    public static NotifyType parse(String str) {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                a.d("notify_type", "parse json string error " + e.getMessage());
            }
            return parse(jSONObject);
        }
        jSONObject = null;
        return parse(jSONObject);
    }

    public static NotifyType parse(JSONObject jSONObject) {
        boolean z = true;
        NotifyType notifyType = new NotifyType();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(VIBRATE)) {
                    notifyType.setVibrate(jSONObject.getInt(VIBRATE) != 0);
                }
                if (!jSONObject.isNull(LIGHTS)) {
                    boolean z2;
                    if (jSONObject.getInt(LIGHTS) != 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    notifyType.setLights(z2);
                }
                if (!jSONObject.isNull(SOUND)) {
                    if (jSONObject.getInt(SOUND) == 0) {
                        z = false;
                    }
                    notifyType.setSound(z);
                }
            } catch (JSONException e) {
                a.d("notify_type", "parse json obj error " + e.getMessage());
            }
        } else {
            a.d("notify_type", "no such tag notify_type");
        }
        return notifyType;
    }

    public String toString() {
        return "NotifyType{vibrate=" + this.vibrate + ", lights=" + this.lights + ", sound=" + this.sound + '}';
    }
}
