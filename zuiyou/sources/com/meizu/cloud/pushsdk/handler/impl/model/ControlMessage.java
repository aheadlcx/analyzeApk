package com.meizu.cloud.pushsdk.handler.impl.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import org.json.JSONException;
import org.json.JSONObject;

public class ControlMessage implements Parcelable {
    public static final Creator<ControlMessage> CREATOR = new Creator<ControlMessage>() {
        public ControlMessage createFromParcel(Parcel parcel) {
            return new ControlMessage(parcel);
        }

        public ControlMessage[] newArray(int i) {
            return new ControlMessage[i];
        }
    };
    public static final String TAG = "ControlMessage";
    private Control control;
    private String controlMessage;
    private Statics statics;

    public ControlMessage(String str, String str2, String str3) {
        this.controlMessage = str;
        if (TextUtils.isEmpty(str)) {
            this.control = new Control();
            this.statics = new Statics();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null) {
                this.control = Control.parse(jSONObject.getJSONObject(Control.TAG));
                this.statics = Statics.parse(jSONObject.getJSONObject(Statics.TAG));
                this.statics.setDeviceId(str2);
                this.statics.setSeqId(str3);
            }
        } catch (JSONException e) {
            this.control = new Control();
            this.statics = new Statics();
            a.d(TAG, "parse control message error " + e.getMessage());
        }
    }

    protected ControlMessage(Parcel parcel) {
        this.controlMessage = parcel.readString();
        this.control = (Control) parcel.readParcelable(Control.class.getClassLoader());
        this.statics = (Statics) parcel.readParcelable(Statics.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.controlMessage);
        parcel.writeParcelable(this.control, i);
        parcel.writeParcelable(this.statics, i);
    }

    public String getControlMessage() {
        return this.controlMessage;
    }

    public void setControlMessage(String str) {
        this.controlMessage = str;
    }

    public Control getControl() {
        return this.control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public Statics getStatics() {
        return this.statics;
    }

    public void setStatics(Statics statics) {
        this.statics = statics;
    }

    public static ControlMessage parse(String str) {
        ControlMessage controlMessage = new ControlMessage();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject != null) {
                controlMessage.setControl(Control.parse(jSONObject.getJSONObject(Control.TAG)));
                controlMessage.setStatics(Statics.parse(jSONObject.getJSONObject(Statics.TAG)));
            }
        } catch (Exception e) {
            a.d(TAG, "parse control message error " + e.getMessage());
            controlMessage.setStatics(new Statics());
            controlMessage.setControl(new Control());
        }
        return controlMessage;
    }

    public String toString() {
        return "ControlMessage{controlMessage='" + this.controlMessage + '\'' + ", control=" + this.control + ", statics=" + this.statics + '}';
    }
}
