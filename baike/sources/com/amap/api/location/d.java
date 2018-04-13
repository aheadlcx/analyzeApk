package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d implements Creator<DPoint> {
    d() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new DPoint(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DPoint[i];
    }
}
