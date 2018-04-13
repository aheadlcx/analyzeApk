package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class WakeuperResult implements Parcelable {
    public static final Creator<WakeuperResult> CREATOR = new Creator<WakeuperResult>() {
        public WakeuperResult createFromParcel(Parcel parcel) {
            return new WakeuperResult(parcel);
        }

        public WakeuperResult[] newArray(int i) {
            return new WakeuperResult[i];
        }
    };
    private String json = "";

    public WakeuperResult(Parcel parcel) {
        this.json = parcel.readString();
    }

    public WakeuperResult(String str) {
        if (str != null) {
            this.json = str;
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getResultString() {
        return this.json;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.json);
    }
}
