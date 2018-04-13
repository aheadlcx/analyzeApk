package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class VerifierResult implements Parcelable {
    public static final Creator<VerifierResult> CREATOR = new Creator<VerifierResult>() {
        public VerifierResult createFromParcel(Parcel parcel) {
            return new VerifierResult(parcel);
        }

        public VerifierResult[] newArray(int i) {
            return new VerifierResult[i];
        }
    };
    public String dcs = "";
    private String json = "";
    public boolean ret = false;
    public int rgn = 0;
    public String source = "";
    public String sst;
    public int suc = 0;
    public String trs = "";
    public String vid = "";

    public VerifierResult(Parcel parcel) {
        this.json = parcel.readString();
    }

    public VerifierResult(String str) {
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
