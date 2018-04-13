package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class MeasureValue$1 implements Creator<MeasureValue> {
    MeasureValue$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return b(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public MeasureValue b(Parcel parcel) {
        return MeasureValue.a(parcel);
    }

    public MeasureValue[] a(int i) {
        return new MeasureValue[i];
    }
}
