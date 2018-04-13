package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class UnderstanderResult implements Parcelable {
    public static final Creator<UnderstanderResult> CREATOR = new Creator<UnderstanderResult>() {
        public UnderstanderResult a(Parcel parcel) {
            return new UnderstanderResult(parcel);
        }

        public UnderstanderResult[] a(int i) {
            return new UnderstanderResult[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    };
    private String a = "";

    public UnderstanderResult(Parcel parcel) {
        this.a = parcel.readString();
    }

    public UnderstanderResult(String str) {
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
