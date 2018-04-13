package com.alibaba.mtl.appmonitor.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class DimensionValueSet$1 implements Creator<DimensionValueSet> {
    DimensionValueSet$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return b(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public DimensionValueSet b(Parcel parcel) {
        return DimensionValueSet.a(parcel);
    }

    public DimensionValueSet[] a(int i) {
        return new DimensionValueSet[i];
    }
}
