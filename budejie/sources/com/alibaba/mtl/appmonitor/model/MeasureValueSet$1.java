package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class MeasureValueSet$1 implements Creator<MeasureValueSet> {
    MeasureValueSet$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return b(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public MeasureValueSet b(Parcel parcel) {
        return MeasureValueSet.a(parcel);
    }

    public MeasureValueSet[] a(int i) {
        return new MeasureValueSet[i];
    }
}
