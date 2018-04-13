package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RecognizerResult implements Parcelable {
    public static final Creator<RecognizerResult> CREATOR = new Creator<RecognizerResult>() {
        public RecognizerResult a(Parcel parcel) {
            return new RecognizerResult(parcel);
        }

        public RecognizerResult[] a(int i) {
            return new RecognizerResult[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    };
    private String a = "";

    public RecognizerResult(Parcel parcel) {
        this.a = parcel.readString();
    }

    public RecognizerResult(String str) {
        if (str != null) {
            this.a = str;
        }
    }

    public int describeContents() {
        return 0;
    }

    public String getResultString() {
        return this.a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
