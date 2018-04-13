package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IdentityResult implements Parcelable {
    public static final Creator<IdentityResult> CREATOR = new Creator<IdentityResult>() {
        public IdentityResult a(Parcel parcel) {
            return new IdentityResult(parcel);
        }

        public IdentityResult[] a(int i) {
            return new IdentityResult[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    };
    private String a;

    private IdentityResult(Parcel parcel) {
        this.a = "";
        this.a = parcel.readString();
    }

    public IdentityResult(String str) {
        this.a = "";
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
