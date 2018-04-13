package com.meizu.cloud.pushsdk.handler.impl.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class Control implements Parcelable {
    public static final String CACHED = "cached";
    public static final String CACHENUM = "cacheNum";
    public static final Creator<Control> CREATOR = new Creator<Control>() {
        public Control createFromParcel(Parcel parcel) {
            return new Control(parcel);
        }

        public Control[] newArray(int i) {
            return new Control[i];
        }
    };
    public static final String PUSH_TYPE = "pushType";
    public static final String TAG = "ctl";
    private int cacheNum;
    private int cached;
    private int pushType;

    protected Control(Parcel parcel) {
        this.pushType = parcel.readInt();
        this.cached = parcel.readInt();
        this.cacheNum = parcel.readInt();
    }

    public Control(JSONObject jSONObject) {
        parse(jSONObject);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.pushType);
        parcel.writeInt(this.cached);
        parcel.writeInt(this.cacheNum);
    }

    public int getPushType() {
        return this.pushType;
    }

    public void setPushType(int i) {
        this.pushType = i;
    }

    public int getCached() {
        return this.cached;
    }

    public void setCached(int i) {
        this.cached = i;
    }

    public int getCacheNum() {
        return this.cacheNum;
    }

    public void setCacheNum(int i) {
        this.cacheNum = i;
    }

    public static Control parse(String str) {
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

    public static Control parse(JSONObject jSONObject) {
        Control control = new Control();
        if (jSONObject != null) {
            try {
                if (!jSONObject.isNull(PUSH_TYPE)) {
                    control.setPushType(jSONObject.getInt(PUSH_TYPE));
                }
                if (!jSONObject.isNull(CACHED)) {
                    control.setCached(jSONObject.getInt(CACHED));
                }
                if (!jSONObject.isNull(CACHENUM)) {
                    control.setCacheNum(jSONObject.getInt(CACHENUM));
                }
            } catch (JSONException e) {
                a.d(TAG, " parse control message error " + e.getMessage());
            }
        } else {
            a.d(TAG, "no control message can parse ");
        }
        return control;
    }

    public String toString() {
        return "Control{pushType=" + this.pushType + ", cached=" + this.cached + ", cacheNum=" + this.cacheNum + '}';
    }
}
