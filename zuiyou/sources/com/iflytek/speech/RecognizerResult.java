package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RecognizerResult implements Parcelable {
    public static final Creator<RecognizerResult> CREATOR = new Creator<RecognizerResult>() {
        public RecognizerResult createFromParcel(Parcel parcel) {
            return new RecognizerResult(parcel);
        }

        public RecognizerResult[] newArray(int i) {
            return new RecognizerResult[i];
        }
    };
    private String json = "";

    public RecognizerResult(Parcel parcel) {
        this.json = parcel.readString();
    }

    public RecognizerResult(String str) {
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
