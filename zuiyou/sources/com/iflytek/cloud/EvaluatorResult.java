package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class EvaluatorResult implements Parcelable {
    public static final Creator<EvaluatorResult> CREATOR = new Creator<EvaluatorResult>() {
        public EvaluatorResult a(Parcel parcel) {
            return new EvaluatorResult(parcel);
        }

        public EvaluatorResult[] a(int i) {
            return new EvaluatorResult[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }
    };
    private String a;

    private EvaluatorResult(Parcel parcel) {
        this.a = "";
        this.a = parcel.readString();
    }

    public EvaluatorResult(String str) {
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
