package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UnderstanderResult implements Parcelable {
    public static final Creator<UnderstanderResult> CREATOR = new Creator<UnderstanderResult>() {
        public UnderstanderResult createFromParcel(Parcel parcel) {
            return new UnderstanderResult(parcel);
        }

        public UnderstanderResult[] newArray(int i) {
            return new UnderstanderResult[i];
        }
    };
    private String mXml = "";

    public UnderstanderResult(Parcel parcel) {
        this.mXml = parcel.readString();
    }

    public UnderstanderResult(String str) {
        if (str != null) {
            this.mXml = str;
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getResultString() {
        return this.mXml;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mXml);
    }
}
